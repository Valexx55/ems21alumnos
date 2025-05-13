package edu.kyndryl.academy.msalumnos.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.kyndryl.academy.msalumnos.repository.UsuarioRepository;
import edu.kyndryl.academy.msalumnos.security.model.UserDetailImpl;
import edu.kyndryl.academy.msalumnos.security.model.Usuario;


public class UserDetailServiceImp implements UserDetailsService {
	
	@Autowired
	UsuarioRepository uRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		
			Usuario usuario = uRepository.findByNombre(username).orElseThrow(()-> new UsernameNotFoundException("El usuario no existe con ese nombre"));
			userDetails = new UserDetailImpl(usuario);
			
		return userDetails;
	}

}