package cm.pdl.plandelocalisation.plan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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

    public String printPlace() {
        if (StringUtils.isEmpty(address.getSuburb()) && StringUtils.isEmpty(address.getCity()) && StringUtils.isEmpty(address.getCountry())) {
            return "";
        } else if (StringUtils.isEmpty(address.getSuburb()) && StringUtils.isEmpty(address.getCity())) {
            return address.getCountry();
        } else if (StringUtils.isEmpty(address.getSuburb()) && StringUtils.isEmpty(address.getCountry())) {
            return address.getCity();
        } else if (StringUtils.isEmpty(address.getCity()) && StringUtils.isEmpty(address.getCountry())) {
            return address.getSuburb();
        } else {
            return address.getSuburb() + " - " + address.getCity() + " - " + address.getCountry();
        }
    }


}
