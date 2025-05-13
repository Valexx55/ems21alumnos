package edu.kyndryl.academy.msalumnos.security.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component //con eso, hago que Spring instancie la clase al iniciar el contexto
public class TokenUtil {
	
	//obtenemos el valor del fichero de propiedades
	@Value("${duracion}")
	private int duracion;
	
	@Value("${clavejwt}")
	private String clavejwt;
	
	private static Logger log = LoggerFactory.getLogger(TokenUtil.class);
	
	
	/**
	 * Creamos el token en base a los parámetros de entrada que identifican al usuario
	 * @param nombre
	 * @param email
	 * @param ca
	 * @return
	 */
	public String crearToken (String nombre, String email, Collection<? extends GrantedAuthority> ca)
	{
		String tokenNuevo = null;
		
			long tiempo_expiracion = duracion;// * 1000;
			Date fecha_expiracion = new Date(System.currentTimeMillis()+  tiempo_expiracion);
			
			log.debug("FECHA DE CADUCIDAD "  + fecha_expiracion);
			
			Map<String, Object> extra = new HashMap<>();
			extra.put("nombre", nombre);
			//la lista de roles, la paso a un String ROLE_ADMIN, ROLE_USER
			String roles = ca.stream().map(c->c.getAuthority()).reduce((ac, au)-> au.concat(",")).get();
			extra.put("rol", roles);
			
			tokenNuevo = Jwts.builder()
			.setSubject(email)
			.setExpiration(fecha_expiracion)
			.setHeaderParam("typ", "JWT")
			.addClaims(extra)
			.signWith(Keys.hmacShaKeyFor(clavejwt.getBytes()))
			.compact();
			
			log.info("NUEVO TOKEN GENERADO = " + tokenNuevo);
		
		return tokenNuevo;
	}
	
	/**
	 * Validar el token y a partir de él, construimos la información del usuario para el contexto de seguridad de Spring
	 * @param token
	 * @return
	 */
	public UsernamePasswordAuthenticationToken getAuthentication (String token)
	{
		UsernamePasswordAuthenticationToken usuarioConToken = null;
		
		try {
			
			//TODO extraer el nombre también
			Claims claims = Jwts
							.parserBuilder()
							.setSigningKey(clavejwt.getBytes())
							.build()
							.parseClaimsJws(token).getBody();
			
			log.info("Claims " + claims.toString());
			
			String email = claims.getSubject();
			String rol = claims.get("rol", String.class);
			
			String nombre = claims.get("nombre", String.class);
			
			
			List<GrantedAuthority> listaRoles = List.of(new SimpleGrantedAuthority(rol));
			
			usuarioConToken = new UsernamePasswordAuthenticationToken(email, nombre,listaRoles);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR AL PARSEAR EL TOKEN", e);
		}
		
		
		return usuarioConToken;
	}

}