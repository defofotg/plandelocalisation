package cm.pdl.plandelocalisation.plan.service;

import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Georges DEFO
 * @date 14/06/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PlanGeneratorService {

    private static final String TITLE = "PLAN DE LOCALISATION";
    private static final String SUBTITLE = "Détails de votre localisation";

    @Value("${gmaps.key}")
    private String key;

    @Value("${gmaps.url}")
    private String baseUrl;

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
        Image image = Image.getInstance("https://maps.googleapis.com/maps/api/staticmap?" +
                "key=AIzaSyDuqwm4XHdzpFZnog40J_e3JKirsA83pi4" +
                "&center=4.093115621063734,9.7473994" +
                "&size=500x500" +
                "&zoom=18" +
                "&maptype=roadmap" +
                "&markers=icon:https://map.what3words.com/map/marker.png|4.093115621063734,9.7473994");
        image.setAlignment(Image.MIDDLE);

        // Adding image to the document
        document.add(paragraph);
        document.add(paragraph2);
        document.add(image);
        document.close();
    }

    private Paragraph generateParagraph(String fontName, int fontSize, String text) {
        Font fontTitle = FontFactory.getFont(fontName);
        fontTitle.setSize(fontSize);
        return new Paragraph(text, fontTitle);
    }

    private String url(LocationDTO location){

    }

}
