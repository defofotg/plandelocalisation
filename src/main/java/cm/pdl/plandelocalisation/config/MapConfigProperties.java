package cm.pdl.plandelocalisation.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

/**
 * @author Georges DEFO
 * @date 28/06/2022
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "map")
public class MapConfigProperties {

    @Getter
    @Setter
    @NotBlank
    private String baseUrl;

    @Getter
    @Setter
    @NotBlank
    private String key;

    @Getter
    @Setter
    @URL
    private String url;

    @Getter
    @Setter
    @NotBlank
    private String zoom;

    @Getter
    @Setter
    @NotBlank
    private String mapType;

    @Getter
    @Setter
    @URL
    private String marker;

    @Getter
    @Setter
    private Size size = new Size();

    @Getter
    @Setter
    public static class Size {

        @NotBlank
        private String height;

        @NotBlank
        private String width;
    }

    @Getter
    @Setter
    private Details details = new Details();

    @Getter
    @Setter
    public static class Details {

        @NotBlank
        private String url;
    }
}


