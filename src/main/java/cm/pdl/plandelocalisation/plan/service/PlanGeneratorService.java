package cm.pdl.plandelocalisation.plan.service;

import cm.pdl.plandelocalisation.config.MapConfigProperties;
import cm.pdl.plandelocalisation.plan.PlanInterface;
import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @author Georges DEFO
 * @date 14/06/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PlanGeneratorService implements PlanInterface {

    private static final String TITLE = "PLAN DE LOCALISATION";
    private static final String SUBTITLE = "Détails de votre localisation";
    private static final String URL_KEY_PART = "?key=";

    private final MapConfigProperties mapConfigProperties;

    private final WebClient webClient;

    public void export(LocationDTO location, HttpServletResponse response) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph paragraph = generateParagraph(
                FontFactory.HELVETICA_BOLDOBLIQUE,
                36,
                TITLE);

        Paragraph paragraph2 = generateParagraph(
                FontFactory.HELVETICA_OBLIQUE,
                26,
                SUBTITLE);

        // Creating an Image object
        String imageUrl = getStaticMapImageUrl(location);

        if (imageUrl == null) {
            throw new IllegalArgumentException("The image URL is null, the map cannot be generated.");
        }

        Image image = Image.getInstance(imageUrl);
        image.setAlignment(Image.MIDDLE);
        image.setBorder(Image.BOX);
        image.setBorderColor(Color.LIGHT_GRAY);
        image.setBorderWidth(1f);

        // Adding image to the document
        document.add(paragraph);
        document.add(paragraph2);

        addSpace(document, 1);
        PlaceDTO place = retrieveLocationDetails(location);
        if (place == null) {
            log.debug(String.format("Impossible de récupérer les détails sur la location lat: [%s] - long: [%s]",
                    location.getLatitude(),
                    location.getLongitude()));
        } else {
            Paragraph paragraph3 = generateParagraph(
                    FontFactory.HELVETICA_BOLD,
                    14,
                    place.getDisplay_name());
            paragraph3.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph3);
        }

        addSpace(document, 2);
        document.add(image);
        document.close();
    }

    @Override
    public PlaceDTO retrieveLocationDetails(LocationDTO locationDTO) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/reverse")
                        .queryParam("lat", locationDTO.getLatitude())
                        .queryParam("lon", locationDTO.getLongitude())
                        .queryParam("format", "jsonv2")
                        .build())
                .retrieve()
                .bodyToMono(PlaceDTO.class)
                .block();
    }

    private Paragraph generateParagraph(String fontName, int fontSize, String text) {
        Font fontTitle = FontFactory.getFont(fontName);
        fontTitle.setSize(fontSize);
        return new Paragraph(text, fontTitle);
    }

    private void addSpace(Document doc, int nbSpaces) throws DocumentException {
        for (int i = 0; i < nbSpaces; i++) {
            doc.add(Chunk.NEWLINE);
        }
    }

    private String getStaticMapImageUrl(LocationDTO location) {
        if (location == null) {
            log.debug("Location object is null. Map url cannot be generated.");
            return null;
        }

        return mapConfigProperties.getUrl() +
                URL_KEY_PART +
                mapConfigProperties.getKey() +
                "&center=" + location.getLatitude() + "," + location.getLongitude() +
                "&size=" + mapConfigProperties.getSize().getHeight() + "x" + mapConfigProperties.getSize().getWidth() +
                "&zoom=" + mapConfigProperties.getZoom() +
                "&maptype=" + mapConfigProperties.getMapType() +
                "&markers=icon:" + mapConfigProperties.getMarker() + "|" + location.getLatitude() + "," + location.getLongitude();
    }

}
