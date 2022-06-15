package cm.pdl.plandelocalisation.plan.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public void export(HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("This is a title.", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("This is a paragraph.", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

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

}
