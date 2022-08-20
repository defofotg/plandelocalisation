package cm.pdl.plandelocalisation.plan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Georges DEFO
 * @date 10/08/2022
 */
@RequiredArgsConstructor
@Getter
@Setter
public class GeoapifyDTO {

    private String suburb;
    private String city;
    private String county;
    private String state;
    private String postcode;
    private String country;
    private String country_code;
    private String road;
    private String lon;
    private String lat;
    private String state_code;
    private String formatted;
}
