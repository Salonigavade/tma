package com.capgemini.TMA;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication(scanBasePackages = "com.capgemini")
@EntityScan(basePackages = "com.capgemini.entity")
@EnableJpaRepositories(basePackages = "com.capgemini.repository")
@EnableSwagger2
@EnableOpenApi
public class TmaApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(TmaApplication.class, args);
	}

	/**
	 * Open api employee store.
	 *
	 * @return the docket
	 */
	@Bean
    public Docket openApiEmployeeStore() {
      return new Docket(DocumentationType.OAS_30)
          .groupName("open-api-tma")
          .select()
          .paths(TMAPaths())
          .build();
    }
    
    /**
     * TLTA paths.
     *
     * @return the predicate
     */
    private Predicate<String> TMAPaths() {
      return regex(".*/api/.*");
    }
}
