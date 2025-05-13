package edu.kyndryl.academy.msalumnos.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//esta es la clase que realmente usará Spring para acceder a las credenciales de un usuario

public class UserDetailImpl implements UserDetails {
	
	private Usuario usuario;
	
	public UserDetailImpl(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
			
			//hacemos una equivalencia entre Rol Y Autoridad
			this.usuario.getRoles().forEach(
					rol -> {
						//TODO revisar el apaño
						String strRol = null;
						if (rol.getId()==1l)
						{
							strRol = "ROLE_ADMIN";
						} else {
							strRol = "ROLE_USER";
						}
						authorities.add(new SimpleGrantedAuthority(strRol));
					}
					);
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return (!this.usuario.getBloqueada());
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.usuario.getActivada();
	}
	
	public String getNombre ()
	{
		return this.usuario.getNombre();
	}
	
	
	

}
