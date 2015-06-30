package com.agami.leavemanagement.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.agami.leavemanagement.dao.UserDao;
import com.agami.leavemanagement.model.User;

@Service("userDetailService")
public class UserDetailService implements UserDetailsService {
	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.getUser(username);
		if (user != null) {
			Collection<? extends GrantedAuthority> authorities = AuthorityUtils
					.createAuthorityList(user.getAuthority());
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), authorities);
		} else {
			
			throw new UserDeniedAuthenticationException("Either user does't activated or does't found");
		}

	}

}
