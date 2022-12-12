package cm.pdl.plandelocalisation.plan.service.impl.reverseGeocode;

import cm.pdl.plandelocalisation.plan.service.LocationInformationService;
import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Georges DEFO
 * @date 25/07/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NominatimService implements LocationInformationService {

    private final WebClient webClient;

    @Override
    public PlaceDTO getInformation(LocationDTO locationDTO) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/reverse")
                        .queryParam("lat", locationDTO.getLatitude())
                        .queryParam("lon", locationDTO.getLongitude())
                        .queryParam("format", "jsonv2")
                        .build())
                .retrieve()
                .bodyToMono(PlaceDTO.class)
                .block();
    }
}
