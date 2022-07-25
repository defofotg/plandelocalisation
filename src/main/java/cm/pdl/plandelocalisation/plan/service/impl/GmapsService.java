package cm.pdl.plandelocalisation.plan.service.impl;

import cm.pdl.plandelocalisation.config.MapConfigProperties;
import cm.pdl.plandelocalisation.plan.service.StaticMapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

/**
 * @author Georges DEFO
 * @date 24/07/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GmapsService implements StaticMapService {

    private final MapConfigProperties mapConfigProperties;

    private final WebClient webClientGmaps;

    @Override
    public byte[] generateMap(String longitude, String latitude) {
        if (longitude == null || latitude == null) {
            log.debug("Location object is null. Map url cannot be generated.");
            return null;
        }

        return webClientGmaps
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/maps/api/staticmap")
                        .queryParam("key", mapConfigProperties.getKey())
                        .queryParam("center", latitude + "," + longitude)
                        .queryParam("size", mapConfigProperties.getSize().getWidth() + "x" + mapConfigProperties.getSize().getHeight())
                        .queryParam("zoom", mapConfigProperties.getZoom())
                        .queryParam("maptype", mapConfigProperties.getMapType())
                        .queryParam("markers", "icon:" + mapConfigProperties.getMarker() + "|" + latitude + "," + longitude)
                        .build())
                .accept(MediaType.IMAGE_PNG)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
    }

    @Override
    public String generateBase64Map(String longitude, String latitude) {
        byte[] image = generateMap(longitude, latitude);

        if (image == null) {
            log.debug("The map download failed");
            return null;
        }

        return Base64.getEncoder().encodeToString(image);
    }
}
