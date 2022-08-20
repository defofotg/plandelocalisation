package cm.pdl.plandelocalisation.plan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AddressDTO implements Serializable {
    private String amenity;
    private String neighbourhood;
    private String suburb;
    private String village;
    private String city;
    private String municipality;
    private String county;
    private String state;
    private String country;
    @JsonProperty("ISO3166-2-lvl4")
    public String iSO31662Lvl4;

    public String planDisplayPlaceName() {
        if (StringUtils.isEmpty(neighbourhoodOrSuburb()) && StringUtils.isEmpty(cityOrVillage()) && StringUtils.isEmpty(country)) {
            return "";
        } else if (StringUtils.isEmpty(neighbourhoodOrSuburb()) && StringUtils.isEmpty(cityOrVillage())) {
            return country;
        } else if (StringUtils.isEmpty(neighbourhoodOrSuburb()) && StringUtils.isEmpty(country)) {
            return cityOrVillage();
        } else if (StringUtils.isEmpty(cityOrVillage()) && StringUtils.isEmpty(country)) {
            return neighbourhoodOrSuburb();
        } else if (StringUtils.isEmpty(neighbourhoodOrSuburb())) {
            return cityOrVillage() + " - " + country;
        } else if (StringUtils.isEmpty(cityOrVillage())) {
            return neighbourhoodOrSuburb() + " - " + country;
        } else if (StringUtils.isEmpty(country)) {
            return neighbourhoodOrSuburb() + " - " + cityOrVillage();
        }
        return neighbourhoodOrSuburb() + " - " + cityOrVillage() + " - " + country;
    }

    private String neighbourhoodOrSuburb() {
        if (!StringUtils.isEmpty(suburb)) {
            return suburb;
        } else if (!StringUtils.isEmpty(neighbourhood)) {
            return neighbourhood;
        }
        return "";
    }

    private String cityOrVillage() {
        if (!StringUtils.isEmpty(city)) {
            return city;
        } else if (!StringUtils.isEmpty(village)) {
            return village;
        }
        return "";
    }
}
