package edu.kyndryl.academy.msalumnos.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.kyndryl.academy.msalumnos.filtro.JWTFiltroATH;
import edu.kyndryl.academy.msalumnos.filtro.JWTFiltroATZ;
import edu.kyndryl.academy.msalumnos.security.service.UserDetailServiceImp;

/**
 * En esta clase, configuramos la seguridad local de nuestro microservicio
 */

@EnableWebSecurity//indicamos que aquí se configura la seguridad
@Configuration
public class ConfiguracionSeguridad {
	
	//UserDetailsService es la fuente de los datos de los usuarios para Spring
	
	@Bean //este objeto es usado en la configuración de spring / contexto
	@Profile({"dev"})
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
	@Profile({"dev"})
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
	
	
	@Bean
	@Profile({"prod"})
	public PasswordEncoder passordEncoder ()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Profile({"prod"})
	public UserDetailsService userDetailServiceBD ()
	{
		return new UserDetailServiceImp();
	}
	
	@Bean
	@Profile({"prod"})
	public AuthenticationManager authenticationManager (AuthenticationConfiguration aConfiguration) throws Exception
	{
		return aConfiguration.getAuthenticationManager(); //esto por defecto, pilla tu UserDetailService y tu PaswordEncoder configurados
	}
	
	
	
	@Bean //este clase representa la política de seguridad
	@Profile({"prod"})//indicamos el perfil/entorno al que aplica esta configuración
	public UsernamePasswordAuthenticationFilter crearFiltroAutentication (AuthenticationManager am)
	{
		JWTFiltroATH jwtFiltroATH = null;
		
			jwtFiltroATH = new JWTFiltroATH();
			jwtFiltroATH.setAuthenticationManager(am);
			//definimos la url con la que se activa
			jwtFiltroATH.setFilterProcessesUrl("/alumno/login");
		
		
		return jwtFiltroATH;
	}
	
	
	@Bean //este clase representa la política de seguridad
	@Profile({"prod"})//indicamos el perfil/entorno al que aplica esta configuración
	public SecurityFilterChain filtroProduccionJWT (HttpSecurity httpSecurity, AuthenticationManager am, JWTFiltroATH jAth, JWTFiltroATZ jAtz) throws Exception
	{
		
		return httpSecurity.csrf(c -> c.disable()).authenticationManager(am)
				.authorizeHttpRequests
			(auth -> auth.requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN")
				.requestMatchers("/alumno/**").authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(sesion-> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilter(jAth)
				.addFilterBefore(jAtz, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
	
	//para configuraciones a nivel global, más prioridad, más para recursos vs HttpSecurity configuraciones a nivel de path
	@Bean
	@Profile({"prod"})
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return web -> web
	        .ignoring()
	        //.requestMatchers("/alumno/**");//sólo para demostrar el caso correcto de test integral en el servidor
	        .requestMatchers("/alumno/obtenerFoto/**");
	}
	
	//TODO GENERAR LAS PASSWORDS DE LOS USUARIOS CIFRADAS
	/*public static void main(String[] args) {
		String pwd = "user";
		var cripto = new BCryptPasswordEncoder();
		String pwd_ucodificada = cripto.encode(pwd);
		System.out.println("PWD USER " +pwd_ucodificada);
		
		String pwda = "admin";
		String pwd_acodificada = cripto.encode(pwda);
		System.out.println("PWD USER " +pwd_acodificada);
		
	}*/
	
	
	

}
