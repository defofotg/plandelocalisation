package cm.pdl.plandelocalisation.plan.service;

import cm.pdl.plandelocalisation.plan.dto.LocationDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Georges DEFO
 * @date 14/06/2022
 */
public interface PlangGeneratorInterface {

    void generate(LocationDTO location, HttpServletResponse response) throws IOException;

}
