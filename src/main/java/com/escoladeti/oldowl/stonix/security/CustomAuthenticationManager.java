package com.escoladeti.oldowl.stonix.security;

import com.escoladeti.oldowl.stonix.forum.model.User;
import com.escoladeti.oldowl.stonix.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private UserRepository repository;



	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		try {
			User user = repository.findByEmail(auth.getName().toString());
			if (auth.getCredentials().toString().equals(user.getPassword())) {
				return auth;
			}
		} catch (NullPointerException e){
			throw new BadCredentialsException("Usuário não cadastrado!");
		}
		throw new BadCredentialsException("Senha incorreta");
	}

}
