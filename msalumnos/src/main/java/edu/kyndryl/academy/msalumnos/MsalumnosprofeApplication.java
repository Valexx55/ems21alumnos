package edu.kyndryl.academy.msalumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import edu.kyndryl.academy.mscomun.entity.Alumno;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication//clase de configuracion pendiente comentar
@EnableDiscoveryClient //activamos el cliente eureka
@EntityScan({"edu.kyndryl.academy.mscomun.entity", "edu.kyndryl.academy.msalumnos.security.model"})//con esta anotación, ayudo a Spring a localizar las entidades, puesto que se encuentran en un paquete distintos del raíz
//@ComponentScan //esta anotación, sería obligada para localizar COntroller, Service y Repository, si estuvieran en distintos paquetes del raíz
@EnableFeignClients//activamos el feign
public class MsalumnosprofeApplication {

	//TODO comentar mejoras
	public static void main(String[] args) {
		SpringApplication.run(MsalumnosprofeApplication.class, args);
	}
	//comentario para forzar jenkis
	
	@Bean
	@Profile("dev")//le digo que instancie esta clase de Alumno sólo si se lanza en modo dev
	public Alumno alumnoArraque ()
	{
		Alumno alumnoConfig = null;
		
			alumnoConfig = new Alumno("JUAN", 50, "PEREZ", "juan@correo.es");
		
		return alumnoConfig;
	}

}
