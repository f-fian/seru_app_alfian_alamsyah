package com.example.seru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing()
public class SeruApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeruApplication.class, args);
	}


//	@Bean
//	public AuditorAware<String> auditorAware() {
//		return new AuditorAwareImpl();
//	}


}
