package cm.pdl.plandelocalisation.plan.service.impl.reverseGeocode;

import cm.pdl.plandelocalisation.config.DataProviderConfigProperties;
import cm.pdl.plandelocalisation.plan.dto.GeoapifyDTO;
import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import cm.pdl.plandelocalisation.plan.mapper.PlaceMapper;
import cm.pdl.plandelocalisation.plan.service.LocationInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * @author Georges DEFO
 * @date 25/07/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GeopapifyService implements LocationInformationService {

    private final WebClient geoapifyWebClient;
    private final DataProviderConfigProperties dataProviderConfigProperties;
    private final PlaceMapper placeMapper;

    @Override
    public PlaceDTO getInformation(LocationDTO locationDTO) {
        List<GeoapifyDTO> results = geoapifyWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/geocode/reverse")
                        .queryParam("lat", locationDTO.getLatitude())
                        .queryParam("lon", locationDTO.getLongitude())
                        .queryParam("format", "json")
                        .queryParam("apiKey", dataProviderConfigProperties.getGeoapify().getKey())
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<GeoapifyDTO>>() {})
                .block();
        return CollectionUtils.isEmpty(results) ? null : placeMapper.fromGeoapify(results.get(0));
    }
}
