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
public class AddressDTO implements Serializable {
    private String suburb;
    private String village;
    private String city;
    private String municipality;
    private String county;
    private String state;
    private String country;
}
