package cm.pdl.plandelocalisation.config;

import lombok.Getter;
import lombok.NonNull;
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
@ConfigurationProperties(prefix = "reverse.datasource")
public class DataProviderConfigProperties {

    @Getter
    @Setter
    @NonNull
    private OpenCage openCage;

    @Getter
    @Setter
    @NonNull
    private Nominatim nominatim;

    @Getter
    @Setter
    @NonNull
    private Geoapify geoapify;

    @Getter
    @Setter
    public static class OpenCage {

        @NotBlank
        private String key;
    }

    @Getter
    @Setter
    public static class Nominatim {

        @NotBlank
        private String url;
    }

    @Getter
    @Setter
    public static class Geoapify {

        @NotBlank
        private String url;

        @NotBlank
        private String key;
    }
}


