package com.mastercard.mfn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages="com.mastercard.mfn")
@SpringBootApplication
public class MfnApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfnApplication.class, args);
	}

}
