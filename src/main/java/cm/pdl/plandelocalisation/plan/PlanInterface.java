package cm.pdl.plandelocalisation.plan;

import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import com.lowagie.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Georges DEFO
 * @date 14/06/2022
 */
public interface PlanInterface {

    void export(LocationDTO location, HttpServletResponse response) throws IOException, DocumentException;

    PlaceDTO retrieveLocationDetails(LocationDTO locationDTO);

}
