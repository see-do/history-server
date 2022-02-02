package com.UMC.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HistoryApplication {

	public static final String APPLICATION_LOCATIONS =
			"spring.config.location=" + "classpath:application.properties," + "classpath:aws.yml";

	public static void main(String[] args) {
		SpringApplication.run(HistoryApplication.class, args);
//		new SpringApplicationBuilder(HistoryApplication.class).properties(APPLICATION_LOCATIONS).run(args);
	}

}
