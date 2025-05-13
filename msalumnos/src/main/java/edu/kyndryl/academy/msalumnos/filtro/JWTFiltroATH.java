package edu.kyndryl.academy.msalumnos.filtro;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kyndryl.academy.msalumnos.security.model.UserDetailImpl;
import edu.kyndryl.academy.msalumnos.security.service.TokenUtil;
import edu.kyndryl.academy.mscomun.entity.CredencialesAutenticacion;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * En esta clase, validamos que el Usuario existe, usando la autenticación configurada
 * 
 * si existe, generamos un token
 * 
 * el usuario presenta sus credenciales en un petición post 
 */

public class JWTFiltroATH extends UsernamePasswordAuthenticationFilter  {
	
	@Autowired
	private TokenUtil tokenUtil;
	
	private static Logger log = LoggerFactory.getLogger(JWTFiltroATH.class);
	
	
	@Override
		public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
				throws AuthenticationException {
			Authentication authentication = null;
			
			try {
				//leo las credenciales del cuerpo de entrada
				CredencialesAutenticacion credenciales = new ObjectMapper().readValue(request.getReader(), CredencialesAutenticacion.class);
				UsernamePasswordAuthenticationToken unpat = new UsernamePasswordAuthenticationToken(credenciales.user(), credenciales.password(), Collections.emptyList());
				
				AuthenticationManager am = getAuthenticationManager();
				authentication = am.authenticate(unpat);//usará el UserDetailService
				
				
			} catch (Exception e) {
				log.error("ERROR AL AUTENTICAR EL USUARIO", e);
				AuthenticationException ae = new AuthenticationCredentialsNotFoundException("USUARIO NO AUTENTICADO");
				throw ae;
			}
			
			return authentication;
		}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.debug("AUTENTICACIÓN EXITOSA");
		//EXTRAIGO EL USUARIO DEL AUTH
		UserDetailImpl userdetail = (UserDetailImpl)  authResult.getPrincipal();
		
		
		//CREAR EL TOKEN
		String tokenNuevo =  this.tokenUtil.crearToken(userdetail.getNombre(), userdetail.getUsername(), userdetail.getAuthorities());
		//lo incluimos en la cabacera de la respuesta, de donde lo sacara el cliente
		
		response.addHeader("Authorization", "Bearer "+ tokenNuevo);
		response.setHeader("Access-Control-Expose-Headers", "Authorization");//permito que se acceda desde js por el cliente a esta cabecera 
		response.getWriter().flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
	
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
	
		log.debug("AUTENTICACIÓN FALLIDA");
		super.unsuccessfulAuthentication(request, response, failed);
	}

}

