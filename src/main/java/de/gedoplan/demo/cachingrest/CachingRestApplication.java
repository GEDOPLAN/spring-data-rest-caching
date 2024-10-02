package de.gedoplan.demo.cachingrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CachingRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingRestApplication.class, args);
	}

}
