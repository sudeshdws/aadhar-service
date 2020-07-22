package com.demo.aadharservice.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


@RunWith(MockitoJUnitRunner.class)
class SwaggerConfigTest {

    @Test
    void api() {
        String schemes = "";
        Docket d = new Docket(DocumentationType.SWAGGER_2)
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
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        Docket d1 = swaggerConfig.api();
        Assert.assertEquals("swagger", d1.getDocumentationType().getName());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "apiTitle",
                "apiDescription",
                "apiVersion",
                "apiTOSUrl",
                new Contact(
                        "apiContactName",
                        "apiContactUrl",
                        "apiContactEmail"
                ),
                "apiLicense",
                "apiLicenseUrl",
                Collections.emptyList()
        );
    }
}