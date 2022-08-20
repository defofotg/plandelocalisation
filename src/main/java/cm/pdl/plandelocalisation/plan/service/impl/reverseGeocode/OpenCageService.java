package cm.pdl.plandelocalisation.plan.service.impl.reverseGeocode;

import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import cm.pdl.plandelocalisation.plan.service.LocationInformationService;
import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Georges DEFO
 * @date 25/07/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OpenCageService implements LocationInformationService {

    private final JOpenCageGeocoder webClient;

    @Override
    public PlaceDTO getInformation(LocationDTO locationDTO) {

        JOpenCageReverseRequest request = new JOpenCageReverseRequest(
                Double.parseDouble(locationDTO.getLatitude()),
                Double.parseDouble(locationDTO.getLongitude())
        );
        request.setLanguage("fr"); // prioritize results in a specific language using an IETF format language code
        request.setNoDedupe(true); // don't return duplicate results
        request.setLimit(5); // only return the first 5 results (default is 10)
        request.setNoAnnotations(true); // exclude additional info such as calling code, timezone, and currency
        request.setMinConfidence(3); // restrict to results with a confidence rating of at least 3 (out of 10)

        JOpenCageResponse response = webClient.reverse(request);
        return new PlaceDTO();

    }
}
