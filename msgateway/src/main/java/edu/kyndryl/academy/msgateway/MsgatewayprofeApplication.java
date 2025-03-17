package edu.kyndryl.academy.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsgatewayprofeApplication {
	
	/**
	 * PASOS PARA CREAR GATEWAY
	 * 
	 * 1) Nuevo Spring starter project -> add gateway y eureka client
	 * 2) Anotación @EnableDiscoveryClient en el main
	 * 3) Properties / yml
	 */

	public static void main(String[] args) {
		SpringApplication.run(MsgatewayprofeApplication.class, args);
	}

}
