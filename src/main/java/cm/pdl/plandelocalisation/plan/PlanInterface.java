package cm.pdl.plandelocalisation.plan;

import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Georges DEFO
 * @date 14/06/2022
 */
public interface PlanInterface {

    void export(LocationDTO location, HttpServletResponse response) throws IOException;

    PlaceDTO retrieveLocationDetails(LocationDTO locationDTO);

}
