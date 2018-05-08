package com.saberpro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.saberpro.presentation.businessDelegate.IBusinessDelegatorView;
import com.saberpro.utilities.FacesUtils;;




/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Component("zathuraCodeAuthenticationProvider")
public class ZathuraCodeAuthenticationProvider implements AuthenticationProvider {
	/**
	 * Security Implementation
	 */
	@Autowired
	IBusinessDelegatorView businessDelegatorView;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) {

		long codigo = Long.parseLong(authentication.getName());
		String password = authentication.getCredentials().toString();

		try {
			User user = businessDelegatorView.loadByCodigoUsuario(codigo);
			if (user != null) {

				if (passwordEncoder.matches(password, user.getPassword())) {
					
					FacesUtils.putinSession("usuarioDTO",businessDelegatorView.findDataByCodigoUsuario(codigo));  
					FacesUtils.putinSession("usuario",businessDelegatorView.findByCodigoUsuario(codigo)); 
					
					return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),user.getAuthorities());

				} else {
					throw new Exception("Error en codigo o contraseña");
				}
			} else {
				throw new Exception("Error en codigo o contraseña");
			}
		} catch (Exception e) {			
			throw new BadCredentialsException("Error en codigo o contraseña");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
