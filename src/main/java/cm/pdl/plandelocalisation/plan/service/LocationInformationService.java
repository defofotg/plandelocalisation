package cm.pdl.plandelocalisation.plan.service;

import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;

/**
 * @author Georges DEFO
 * @date 25/07/2022
 */
public interface LocationInformationService {

    PlaceDTO getInformation(LocationDTO locationDTO);
}
