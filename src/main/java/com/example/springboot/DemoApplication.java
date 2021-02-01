package com.example.springboot;

import com.example.springboot.entity.SpringProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigurationProperties({SpringProperties.class})
//@EnableSwagger2
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		Environment env = app.run(args).getEnvironment();
		logInfo(env);
	}

	public static void logInfo(Environment env) {
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (StringUtils.isEmpty(contextPath)) {
			contextPath = "/";
		}
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}


		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\t{}://localhost:{}{}\n\t" +
						"External: \t{}://{}:{}{}\n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				serverPort,
				contextPath,
				protocol,
				hostAddress,
				serverPort,
				contextPath,
				env.getActiveProfiles());

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
