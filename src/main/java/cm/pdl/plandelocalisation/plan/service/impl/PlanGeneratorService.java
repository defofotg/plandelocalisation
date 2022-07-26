package cm.pdl.plandelocalisation.plan.service.impl;

import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import cm.pdl.plandelocalisation.plan.dto.UserDTO;
import cm.pdl.plandelocalisation.plan.service.LocationInformationService;
import cm.pdl.plandelocalisation.plan.service.PlangGeneratorInterface;
import cm.pdl.plandelocalisation.plan.service.StaticMapService;
import cm.pdl.plandelocalisation.plan.utils.MapUtils;
import cm.pdl.plandelocalisation.plan.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Georges DEFO
 * @date 14/06/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PlanGeneratorService implements PlangGeneratorInterface {

    private static final String TEMPLATE = "plan.html";

    private final TemplateEngine templateEngine;

    private final StaticMapService staticMapService;

    private final LocationInformationService locationInformationService;

    public void generate(UserDTO user, LocationDTO location, HttpServletResponse response) throws IOException {
        if (location == null) {
            log.debug("Location object is null. Map url cannot be generated.");
            throw new IllegalArgumentException("Location object is null. Map url cannot be generated.");
        }

        Context ctx = new Context();

        PlaceDTO place = locationInformationService.getInformation(location);
        String mapImage = staticMapService.generateBase64Map(location.getLongitude(), location.getLatitude());
        String planID = MapUtils.pdlUniqueIdentifier(place);
        String creationDate = MapUtils.pdlZonedCreationDate();
        String expirationDate = MapUtils.pdlZonedExpirationDate(creationDate);
        String qrCode = null;

        try {
             qrCode = QRCodeGenerator.getQRCodeImage(planID, 200, 200);
        } catch (WriterException we) {
            log.error("QRCode creation failed.");
        }

        ctx.setVariable("user", user);
        ctx.setVariable("identifier", planID);
        ctx.setVariable("location", location);
        ctx.setVariable("place", place);
        ctx.setVariable("mapImage", mapImage);
        ctx.setVariable("qrCode", qrCode);
        ctx.setVariable("creationDate", creationDate);
        ctx.setVariable("expirationDate", expirationDate);

        String htmlContent = templateEngine.process(TEMPLATE, ctx);

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());
        HtmlConverter.convertToPdf(htmlContent, response.getOutputStream(), converterProperties);
    }
}
