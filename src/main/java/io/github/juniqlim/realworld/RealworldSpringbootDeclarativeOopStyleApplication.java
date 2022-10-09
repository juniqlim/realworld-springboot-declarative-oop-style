package io.github.juniqlim.realworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class RealworldSpringbootDeclarativeOopStyleApplication {
	public static void main(String[] args) {
		SpringApplication.run(RealworldSpringbootDeclarativeOopStyleApplication.class, args);
	}

	@Bean
	public KeyPair keyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024);
		return kpg.generateKeyPair();
	}
}
