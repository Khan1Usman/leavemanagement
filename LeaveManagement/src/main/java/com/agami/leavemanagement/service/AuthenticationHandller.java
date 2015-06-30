package com.agami.leavemanagement.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthenticationHandller extends
		SavedRequestAwareAuthenticationSuccessHandler {

	// private RedirectStrategy redirectStrategy = new
	// DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String targetUrl = determineTargetUrl(authentication);
		
		//super.setDefaultTargetUrl(targetUrl);
		super.onAuthenticationSuccess(request, response, authentication);

	}

	protected String determineTargetUrl(Authentication authentication) {
		boolean isValidUser = false;
		
		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_USER")
					|| grantedAuthority.getAuthority().equals("ROLE_ADMIN")
					|| grantedAuthority.getAuthority()
							.equals("ROLE_PROMANAGER")) {
				isValidUser = true;
				break;

			}
		}
		if (isValidUser) {
			return "/successLogin";
		} else {
			throw new IllegalStateException();
		}
	}

}
