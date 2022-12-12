package cm.pdl.plandelocalisation.plan.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Georges DEFO
 * @date 18/08/2022
 */
class AddressDTOTest {

    @Test
    @DisplayName("Calculate place display name when there is no suburb nor neighbourhood")
    void planDisplayPlaceNameNoSuburbNorNeigbourhood() {
        //GIVEN
        AddressDTO address = new AddressDTO();
        address.setCity("Douala V");
        address.setCountry("Cameroun");
        PlaceDTO place = new PlaceDTO();
        place.setAddress(address);

        //WHEN
        String placeName = place.getAddress().planDisplayPlaceName();

        //THEN
        assertThat(placeName).isEqualTo("Douala V - Cameroun");
    }

    @Test
    @DisplayName("Calculate place display name when there is no city nor village")
    void planDisplayPlaceNameNoCityNorVillage() {
        //GIVEN
        AddressDTO suburbAddress = new AddressDTO();
        suburbAddress.setSuburb("Bonamoussadi");
        suburbAddress.setCountry("Cameroun");
        PlaceDTO place = new PlaceDTO();
        place.setAddress(suburbAddress);

        //WHEN
        String placeName = place.getAddress().planDisplayPlaceName();

        //THEN
        assertThat(placeName).isEqualTo("Bonamoussadi - Cameroun");
    }

    @Test
    @DisplayName("Calculate place display name when there is no country")
    void planDisplayPlaceNameNoCountry() {
        //GIVEN
        AddressDTO suburbAddress = new AddressDTO();
        suburbAddress.setSuburb("Bonamoussadi");
        suburbAddress.setCity("Douala V");
        PlaceDTO place = new PlaceDTO();
        place.setAddress(suburbAddress);

        //WHEN
        String placeName = place.getAddress().planDisplayPlaceName();

        //THEN
        assertThat(placeName).isEqualTo("Bonamoussadi - Douala V");
    }

    @Test
    @DisplayName("Calculate place display name when only country")
    void planDisplayPlaceNameOnlyCountry() {
        //GIVEN
        AddressDTO suburbAddress = new AddressDTO();
        suburbAddress.setCountry("Cameroun");
        PlaceDTO place = new PlaceDTO();
        place.setAddress(suburbAddress);

        //WHEN
        String placeName = place.getAddress().planDisplayPlaceName();

        //THEN
        assertThat(placeName).isEqualTo("Cameroun");
    }

    @Test
    @DisplayName("Calculate place display name when only village")
    void planDisplayPlaceNameOnlyVillage() {
        //GIVEN
        AddressDTO suburbAddress = new AddressDTO();
        suburbAddress.setVillage("Bonangang");
        PlaceDTO place = new PlaceDTO();
        place.setAddress(suburbAddress);

        //WHEN
        String placeName = place.getAddress().planDisplayPlaceName();

        //THEN
        assertThat(placeName).isEqualTo("Bonangang");
    }

    @Test
    @DisplayName("Calculate place display name when only neigbourhood")
    void planDisplayPlaceNameOnlyNeighbourhood() {
        //GIVEN
        AddressDTO suburbAddress = new AddressDTO();
        suburbAddress.setNeighbourhood("Denver");
        PlaceDTO place = new PlaceDTO();
        place.setAddress(suburbAddress);

        //WHEN
        String placeName = place.getAddress().planDisplayPlaceName();

        //THEN
        assertThat(placeName).isEqualTo("Denver");
    }

    @Test
    @DisplayName("Calculate place display name when no data")
    void planDisplayPlaceNameEmpty() {
        //GIVEN
        AddressDTO suburbAddress = new AddressDTO();
        PlaceDTO place = new PlaceDTO();
        place.setAddress(suburbAddress);

        //WHEN
        String placeName = place.getAddress().planDisplayPlaceName();

        //THEN
        assertThat(placeName).isEqualTo("");
    }

    @Test
    @DisplayName("Calculate place display name when data is complete")
    void planDisplayPlaceNameComplete() {
        //GIVEN
        AddressDTO suburbAddress = new AddressDTO();
        suburbAddress.setSuburb("Bonamoussadi");
        suburbAddress.setNeighbourhood("Denver");
        suburbAddress.setCity("Douala V");
        suburbAddress.setVillage("Bonangang");
        suburbAddress.setCountry("Cameroun");
        PlaceDTO place = new PlaceDTO();
        place.setAddress(suburbAddress);

        AddressDTO neighbourhoodAddress = new AddressDTO();
        neighbourhoodAddress.setNeighbourhood("Denver");
        neighbourhoodAddress.setVillage("Bonangang");
        neighbourhoodAddress.setCountry("Cameroun");
        PlaceDTO place2 = new PlaceDTO();
        place2.setAddress(neighbourhoodAddress);

        //WHEN
        String placeName = place.getAddress().planDisplayPlaceName();
        String placeName2 = place2.getAddress().planDisplayPlaceName();

        //THEN
        assertThat(placeName).isEqualTo("Bonamoussadi - Douala V - Cameroun");
        assertThat(placeName2).isEqualTo("Denver - Bonangang - Cameroun");
    }

}