package cm.pdl.plandelocalisation.plan.mapper;

import cm.pdl.plandelocalisation.plan.controller.dto.PlanInputVM;
import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import cm.pdl.plandelocalisation.plan.dto.UserDTO;
import org.mapstruct.Mapper;

/**
 * @author Georges DEFO
 * @date 30/07/2022
 */
@Mapper(componentModel = "spring")
public interface PlanMapper {

    LocationDTO planVMtoLocationDTO(PlanInputVM planInputVM);

    UserDTO planVMtoUserDTO(PlanInputVM planInputVM);
}
