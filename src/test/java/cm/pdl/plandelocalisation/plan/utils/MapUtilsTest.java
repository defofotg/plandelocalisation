package cm.pdl.plandelocalisation.plan.utils;

import cm.pdl.plandelocalisation.plan.dto.AddressDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Georges DEFO
 * @date 19/08/2022
 */
class MapUtilsTest {

    private  final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
    private static final DateTimeFormatter PDL_ID_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyHHmmss");

    @Test
    @DisplayName("Generate unique PDL identifier correctly")
    void pdlUniqueIdentifier() {
        //GIVEN
        AddressDTO address = new AddressDTO();
        address.setSuburb("Bonamoussadi");
        address.setCity("Douala V");
        address.setCountry("Cameroun");
        address.setISO31662Lvl4("CM-LT");
        PlaceDTO place = new PlaceDTO();
        place.setPlace_id("123456789");
        place.setAddress(address);
        String str = "260922145450";
        LocalDateTime dateTime = LocalDateTime.parse(str, PDL_ID_DATE_TIME_FORMATTER);

        //WHEN
        String identifier = MapUtils.pdlUniqueIdentifier(place, dateTime);

        //THEN
        assertThat(identifier).isEqualTo("PDL261045602239CMLT");
    }

    @Test
    @DisplayName("Generate unique PDL identifier with missing address information")
    void pdlUniqueIdentifierFailsNoAddress() {
        //GIVEN
        PlaceDTO place = new PlaceDTO();
        place.setPlace_id("123456789");

        //WHEN
        String identifier = MapUtils.pdlUniqueIdentifier(place, null);

        //THEN
        assertThat(StringUtils.isEmpty(identifier)).isTrue();
    }

    @Test
    @DisplayName("Generate unique PDL identifier with missing place ID")
    void pdlUniqueIdentifierFailsNoPlaceID() {
        //GIVEN
        AddressDTO address = new AddressDTO();
        address.setSuburb("Bonamoussadi");
        address.setCity("Douala V");
        address.setCountry("Cameroun");
        address.setISO31662Lvl4("CM-LT");
        PlaceDTO place = new PlaceDTO();
        place.setAddress(address);

        //WHEN
        String identifier = MapUtils.pdlUniqueIdentifier(place, null);

        //THEN
        assertThat(StringUtils.isEmpty(identifier)).isTrue();
    }

    @Test
    @DisplayName("Calculate the expiration date from a given date")
    void pdlZonedExpirationDate() {
        //GIVEN
        String creationDate = "20-08-2022";

        //WHEN
        String expirationDate = MapUtils.pdlZonedExpirationDate(creationDate);

        //THEN
        assertThat(expirationDate).isEqualTo("20-09-2022");
    }

    @Test
    @DisplayName("Calculate the expiration date from a null date")
    void pdlZonedExpirationDateFromNullDate() {
        //GIVEN
        String creationDate = "";

        //WHEN
        String expirationDate = MapUtils.pdlZonedExpirationDate(creationDate);

        //THEN
        assertThat(StringUtils.isEmpty(expirationDate)).isTrue();
    }

    @Test
    @DisplayName("Calculate the creation date from a date")
    @Disabled
    void pdlZonedCreationDate() {
        //GIVEN
        String str = "2022-20-08 12:15";
        LocalDateTime dateTime = LocalDateTime.parse(str, DATE_TIME_FORMATTER);

        //WHEN
        String creationDate = MapUtils.pdlZonedCreationDate(dateTime, DATE_TIME_FORMATTER);

        //THEN
        assertThat(creationDate).isEqualTo("2022-20-08 12:15");
    }

    @Test
    @DisplayName("Calculate the creation date from a null date")
    void pdlZonedCreationDateFromNullDate() {
        //GIVEN
        LocalDateTime dateTime = null;

        //WHEN
        String creationDate = MapUtils.pdlZonedCreationDate(dateTime, DATE_TIME_FORMATTER);

        //THEN
        assertThat(StringUtils.isEmpty(creationDate)).isTrue();
    }
}