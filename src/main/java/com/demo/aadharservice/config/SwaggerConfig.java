package com.demo.aadharservice.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@ComponentScan("com.demo.aadharservice.config")
public class SwaggerConfig implements WebMvcConfigurer {

	@Value("${swagger.api.title}")
	String apiTitle;
	
	@Value("${swagger.api.description}")
	String apiDescription;
	
	@Value("${swagger.api.version}")
	String apiVersion;
	
	@Value("${swagger.api.tos.url}")
	String apiTOSUrl;
	
	@Value("${swagger.api.contact.name}")
	String apiContactName;
	
	@Value("${swagger.api.contact.url}")
	String apiContactUrl;
	
	@Value("${swagger.api.contact.email}")
	String apiContactEmail;
	
	@Value("${swagger.api.license}")
	String apiLicense;
	
	@Value("${swagger.api.license.url}")
	String apiLicenseUrl;

	
	@Value("${swagger.api.schemes}")
	String schemes;


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry
				.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry
				.addResourceHandler("/webjars*")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.protocols(Sets.newHashSet(schemes))
			.select().apis(RequestHandlerSelectors.any())
		    .paths(Predicates.not(PathSelectors.regex("/error")))
			.paths(Predicates.not(PathSelectors.regex("/auditevents.*")))
			.paths(Predicates.not(PathSelectors.regex("/archaius.*")))
			.paths(Predicates.not(PathSelectors.regex("/autoconfig.*")))
			.paths(Predicates.not(PathSelectors.regex("/beans.*")))
			.paths(Predicates.not(PathSelectors.regex("/configprops.*")))
			.paths(Predicates.not(PathSelectors.regex("/dump.*")))
			.paths(Predicates.not(PathSelectors.regex("/features.*")))
			.paths(Predicates.not(PathSelectors.regex("/info.*")))
			.paths(Predicates.not(PathSelectors.regex("/mappings.*")))
			.paths(Predicates.not(PathSelectors.regex("/trace.*")))
			.paths(Predicates.not(PathSelectors.regex("/env.*")))
			.paths(Predicates.not(PathSelectors.regex("/env/.*")))
			.paths(Predicates.not(PathSelectors.regex("/refresh.*")))
			.paths(Predicates.not(PathSelectors.regex("/actuator")))
			.paths(Predicates.not(PathSelectors.regex("/actuator.json")))
			.paths(Predicates.not(PathSelectors.regex("/refresh.*")))
			.paths(Predicates.not(PathSelectors.regex("/heapdump.*")))
			.paths(Predicates.not(PathSelectors.regex("/hystrix.stream.*")))
			.paths(Predicates.not(PathSelectors.regex("/loggers.*")))
			.paths(Predicates.not(PathSelectors.regex("/metrics.*")))
			.paths(Predicates.not(PathSelectors.regex("/health.*")))
			.paths(Predicates.not(PathSelectors.regex("/service-registry/instance-status")))
			.paths(Predicates.not(PathSelectors.regex("/shutdown.*")))
			.build()
			.apiInfo(apiInfo());

	}
	

	private ApiInfo apiInfo() {
		return new ApiInfo(
			apiTitle, 
			apiDescription, 
			apiVersion, 
			apiTOSUrl,
			new Contact(
				apiContactName, 
				apiContactUrl, 
				apiContactEmail
			), 
			apiLicense,
			apiLicenseUrl, 
			Collections.emptyList()
		);
	}
}