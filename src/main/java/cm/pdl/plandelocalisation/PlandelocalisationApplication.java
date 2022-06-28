package cm.pdl.plandelocalisation;

import cm.pdl.plandelocalisation.config.MapConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MapConfigProperties.class)
public class PlandelocalisationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlandelocalisationApplication.class, args);
	}

}
