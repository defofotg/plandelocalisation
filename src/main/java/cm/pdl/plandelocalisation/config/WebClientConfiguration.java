package cm.pdl.plandelocalisation.config;

import com.byteowls.jopencage.JOpenCageGeocoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

/**
 * @author Georges DEFO
 * @date 29/06/2022
 */
@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

    private final MapConfigProperties mapConfigProperties;
    private final DataProviderConfigProperties dataProviderConfigProperties;

    @Bean
    public WebClient webClient(){
        String baseUrl = dataProviderConfigProperties.getNominatim().getUrl();
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", baseUrl))
                .build();
    }

    @Bean
    public WebClient geoapifyWebClient(){
        String baseUrl = dataProviderConfigProperties.getGeoapify().getUrl();
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", baseUrl))
                .build();
    }

    @Bean
    public WebClient webClientGmaps(){
        return WebClient.builder()
                .baseUrl(mapConfigProperties.getUrl())
                .defaultUriVariables(Collections.singletonMap("url", mapConfigProperties.getUrl()))
                .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("https://gmaps-web-app.herokuapp.com/");
            }
        };
    }

    @Bean
    public JOpenCageGeocoder jOpenCageGeocoder() {
        return new JOpenCageGeocoder(dataProviderConfigProperties.getOpenCage().getKey());
    }
}
