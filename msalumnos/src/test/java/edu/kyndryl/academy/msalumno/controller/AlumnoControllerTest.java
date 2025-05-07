package edu.kyndryl.academy.msalumno.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {MsalumnosprofeApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AlumnoControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	TestRestTemplate template;
	
	@BeforeAll
	public static void antesDeTodosLosTests ()
	{
		System.out.println("antesDeTodosLosTests");
	}
	
	@AfterAll
	public static void despuesDeTodosLosTests ()
	{
		System.out.println("despuesDeTodosLosTests");
	}
	
	@AfterEach
	public void despuesDeCadaTest ()
	{
		System.out.println("despuesDeCadaTest");
	}
	
	
	@BeforeEach
	public void antesDeCadaTest ()
	{
		System.out.println("antesDeCadaTest");
	}
	
	
	
	@Test
	void test() {
		//add autenticación
		this.template = this.template.withBasicAuth("admin", "admin");
		//assertThat(this.template.getForObject("http://localhost:"+port+"/alumno", String.class)).contains("apellido");
	}

}