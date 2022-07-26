package cm.pdl.plandelocalisation.plan.service;

import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.UserDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Georges DEFO
 * @date 14/06/2022
 */
public interface PlangGeneratorInterface {

    void generate(UserDTO userDTO, LocationDTO location, HttpServletResponse response) throws IOException;

}
