package com.TruckBooking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;
@Configuration
@EnableSwagger2
public class SpringFoxWebConfiguration {

	public static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKeys()
	{
		return new ApiKey("Firebase Authentication",AUTHORIZATION_HEADER,"header");
	}
	
	private List<SecurityContext> securityContexts()
	{
		return Arrays.asList(SecurityContext.builder()
							  .securityReferences(sr())
							  .build());
	}
	
	private List<SecurityReference> sr()
	{
		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("Firebase Authentication",new AuthorizationScope[] { scope }));
	}

    @Bean
    public Docket apiv2() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("api-v2")
        		.apiInfo(getInfo())
        		.securityContexts(securityContexts())
        		.securitySchemes(Arrays.asList(apiKeys()))
        		.select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo getInfo()
    {
		return new ApiInfo("TRUCK API DOCUMENTATION","This documentaion gives the detailed explanation of all the services under Truck Api","1.0","Terms of Service",new Contact("Liveasy","Liveasy Website","liveasy mailId"),"License Of Api","LicenSE Url", Collections.emptyList());
    	 
    }

    
}
