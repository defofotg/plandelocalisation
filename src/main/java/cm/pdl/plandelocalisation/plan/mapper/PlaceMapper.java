package cm.pdl.plandelocalisation.plan.mapper;

import cm.pdl.plandelocalisation.plan.dto.GeoapifyDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import org.mapstruct.Mapper;

/**
 * @author Georges DEFO
 * @date 15/08/2022
 */
@Mapper(componentModel = "spring")
public interface PlaceMapper {

    PlaceDTO fromGeoapify(GeoapifyDTO geoapifyDTO);
}
