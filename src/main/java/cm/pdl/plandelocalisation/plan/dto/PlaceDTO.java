package cm.pdl.plandelocalisation.plan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Georges DEFO
 * @date 29/06/2022
 */
@RequiredArgsConstructor
@Getter
@Setter
public class PlaceDTO implements Serializable {
    private String place_id;
    private String lat;
    private String lon;
    private String display_name;
    private AddressDTO address;
}
