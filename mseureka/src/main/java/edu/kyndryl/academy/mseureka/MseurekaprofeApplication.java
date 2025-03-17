package edu.kyndryl.academy.mseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //activamos eureka
public class MseurekaprofeApplication {
	
	/**
	 * PARA CONFIGURAR EUREKA
	 * 
	 * 1) CREAMOS CON EL PPROYECTO CON LA DEPENDENCIA EUREKA SERVER
	 * 2) ADD DEPEDENCIA JAXB GLASFISH
	 * 3) ANOTAMOS CON @EnableEurekaServer
	 * 4) CONFIGURAR LAS PROPERTIES
	 * 
	 */

	public static void main(String[] args) {
		SpringApplication.run(MseurekaprofeApplication.class, args);
	}

}
