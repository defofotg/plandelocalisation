package cm.pdl.plandelocalisation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

/**
 * @author Georges DEFO
 * @date 29/06/2022
 */
@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

    private final MapConfigProperties mapConfigProperties;

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl(mapConfigProperties.getDetails().getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", mapConfigProperties.getDetails().getUrl()))
                .build();
    }
}
