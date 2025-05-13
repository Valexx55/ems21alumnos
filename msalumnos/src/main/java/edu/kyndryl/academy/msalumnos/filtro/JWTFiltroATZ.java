package edu.kyndryl.academy.msalumnos.filtro;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.kyndryl.academy.msalumnos.security.service.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * inspeccionamos el token y en el caso de que exista y sea correcto, lo introducmos en el contexto de seguridad
 * de spring, asociado a esta petición, como diciendo "esta petición ya está autenticada"
 */
@Component
public class JWTFiltroATZ extends OncePerRequestFilter {
	
	@Autowired
	private TokenUtil tokenUtil;
	
	private static Logger log = LoggerFactory.getLogger(JWTFiltroATZ.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// tratamos de identificar el token
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken!=null)
			
		{
				log.debug("La petición viene con token " + bearerToken);
				if (bearerToken.startsWith("Bearer"))
				{
					String token = bearerToken.replace("Bearer ", ""); //extraigo el jwt
					UsernamePasswordAuthenticationToken unpat = tokenUtil.getAuthentication(token);
					SecurityContextHolder.getContext().setAuthentication(unpat);
				}
		}
		// siga
		filterChain.doFilter(request, response);
		
	}

}