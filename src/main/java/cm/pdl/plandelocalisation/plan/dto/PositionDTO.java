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
public class PositionDTO {
    String longitude;
    String latitude;
}
