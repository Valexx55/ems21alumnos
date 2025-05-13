package edu.kyndryl.academy.msalumnos.filtro;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * esta clase se va a ejecutar antes que nuestros endpoints- servicios web- los métodos del controller
 */

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)//hacemos que el filtro se ejecute primero
@WebFilter(urlPatterns = "/**")
public class MiFiltro implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("entrando en filtro");
		/*HttpServletRequest hr = (HttpServletRequest) request;
		String cadenaAuth = hr.getHeader("Authorization");
		System.out.println(cadenaAuth);
		
		byte[] decodeData = Base64.getDecoder().decode(cadenaAuth.substring(6));
		String cadenaDecode = new String(decodeData, StandardCharsets.UTF_8);
		System.out.println(cadenaDecode);*/
		
		chain.doFilter(request, response);//dejarle pasar
		System.out.println("saliendo del filtro");
		
	}

}
