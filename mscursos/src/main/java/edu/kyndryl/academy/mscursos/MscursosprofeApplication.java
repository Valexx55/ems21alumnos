package edu.kyndryl.academy.mscursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("edu.kyndryl.academy.mscomun.entity")//con esta anotación, ayudo a Spring a localizar las entidades, puesto que se encuentran en un paquete distintos del raíz
public class MscursosprofeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscursosprofeApplication.class, args);
	}

}
