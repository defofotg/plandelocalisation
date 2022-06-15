package cm.pdl.plandelocalisation.plan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Georges DEFO
 * @date 15/06/2022
 */
@Getter
@Setter
@RequiredArgsConstructor
public class LocationDTO {
    PositionDTO center;
    ImageSizeDTO size;
    String zoom;
    String mapType;
    MarkerDTO marker;
}
