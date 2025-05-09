package edu.kyndryl.academy.msalumnos.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * En esta clase, configuramos la seguridad local de nuestro microservicio
 */

@EnableWebSecurity//indicamos que aquí se configura la seguridad
@Configuration
public class ConfiguracionSeguridad {
	
	//UserDetailsService es la fuente de los datos de los usuarios para Spring
	
	@Bean //este objeto es usado en la configuración de spring / contexto
	public UserDetailsService usuariosEnMemoria ()
	{
		UserDetailsService userDetailsService = null;
		List<UserDetails> usuarios = null; //lista de usuarios autenticados
		UserDetails usuario1, usuario2 = null;
		
			usuario1 = User.withUsername("user").password("{noop}user").roles("USER").build();
			usuario2 = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
			usuarios = List.of(usuario1, usuario2);
			
			userDetailsService = new InMemoryUserDetailsManager(usuarios);
				
		
		return userDetailsService;
	}
	
	//SecurityFilterChain es la clase que configura el acceso a las peticiones http
	
	@Bean //este objeto es usado en la configuración de spring / contexto para implementar la seguridad
	public SecurityFilterChain filtroSeguridadDesarollo (HttpSecurity httpSecurity) throws Exception
	{
		//1 sólo si eres admin, puedes hacer post
		// todas las peticiones, tiene que estar autenticadas
		
		return httpSecurity.csrf(c -> c.disable()).authorizeHttpRequests
				(auth ->  auth.requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN")
						.requestMatchers("/alumno/**").authenticated()
						)
				.httpBasic(Customizer.withDefaults()).build();
	}
	
	

}
