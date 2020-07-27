package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	public Docket api() {
////		List<SecurityScheme> schemeList = new ArrayList<>();
////		schemeList.add(new ApiKey(HttpHeaders.AUTHORIZATION, "JWT", "header"));
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any())
//				.build()
//				.apiInfo(apiInfo())
////				.securityContexts(Arrays.asList(actuatorSecurityContext()))
//				.securitySchemes(Arrays.asList(apiKey()));
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.title("Sig-Predict REST API Document")
//				.description("work in progress")
//				.termsOfServiceUrl("localhost")
//				.version("1.0")
//				.contact(new Contact("Trung", "https://github.com/","nguyentrung030696@gmail.com"))
//				.license("by trungnd")
//				.licenseUrl("nguyentrung030696@gmail.com")
//				.build();
//	}
//
//	private ApiKey apiKey() {
//		return new ApiKey("jwtToken", "Authorization", "header");
//	}

}
