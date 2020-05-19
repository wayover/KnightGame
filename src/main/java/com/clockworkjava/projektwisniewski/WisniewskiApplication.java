package com.clockworkjava.projektwisniewski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ImportResource("classpath:config/spring-config.xml")

@SpringBootApplication
@EnableScheduling
public class WisniewskiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WisniewskiApplication.class, args);
	}

}
