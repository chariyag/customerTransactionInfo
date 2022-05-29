package com.tab.purchase;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerTransactionInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerTransactionInfoApplication.class, args);
	}


	@Bean
	public OpenAPI openAPI(){
		return new OpenAPI().info(apiInfo());
	}

	public Info apiInfo(){
		Info info = new Info();
		info
				.title("Purchase Order ")
				.description("Swagger API Documentation")
				.version("v_0_0");
        return info;
	}
}
