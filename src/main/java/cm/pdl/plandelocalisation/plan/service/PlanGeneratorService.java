package cm.pdl.plandelocalisation.plan.service;

import cm.pdl.plandelocalisation.config.MapConfigProperties;
import cm.pdl.plandelocalisation.plan.PlanInterface;
import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * @author Georges DEFO
 * @date 14/06/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PlanGeneratorService implements PlanInterface {

    private static final String TEMPLATE = "plan.html";

    private final MapConfigProperties mapConfigProperties;

    private final WebClient webClient;

    private final WebClient webClientGmaps;

    private final TemplateEngine templateEngine;

    public void export(LocationDTO location, HttpServletResponse response) throws IOException {

        Context ctx = new Context();

        PlaceDTO place = retrieveLocationDetails(location);
        String mapImage = getStaticMapImage(location);

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        ctx.setVariable("location", location);
        ctx.setVariable("place", place);
        ctx.setVariable("mapImage", mapImage);
        ctx.setVariable("creationDate", currentDateTime);

        String htmlContent = templateEngine.process(TEMPLATE, ctx);

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());

        HtmlConverter.convertToPdf(htmlContent, response.getOutputStream(), converterProperties);
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

    private String getStaticMapImage(LocationDTO location) {
        if (location == null) {
            log.debug("Location object is null. Map url cannot be generated.");
            return null;
        }

        byte[] image = webClientGmaps
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/maps/api/staticmap")
                        .queryParam("key", mapConfigProperties.getKey())
                        .queryParam("center", location.getLatitude() + "," + location.getLongitude())
                        .queryParam("size", mapConfigProperties.getSize().getWidth() + "x" + mapConfigProperties.getSize().getHeight())
                        .queryParam("zoom", mapConfigProperties.getZoom())
                        .queryParam("maptype", mapConfigProperties.getMapType())
                        .queryParam("markers", "icon:" + mapConfigProperties.getMarker() + "|" + location.getLatitude() + "," + location.getLongitude())
                        .build())
                .accept(MediaType.IMAGE_PNG)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

        if (image == null) {
            log.debug("The map download failed");
            return null;
        }

        return Base64.getEncoder().encodeToString(image);
    }

}
