package com.agami.leavemanagement.controller;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agami.leavemanagement.model.User;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String home(ModelMap model) {
		
		 /* String encodedPassword = encoder.encode("147");
		  System.out.println(encodedPassword + " epassw");
		  System.out.println(encoder.matches("ajay123",encodedPassword));
		  System.out.println(encoder.matches("123",
		  "f6b5edbc67da220de488095178a9535bb35a5e9e65e074499b65c3f6a6d3eeade3c16d197a5b3838"
		  ));*/
		 
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if ((auth instanceof AnonymousAuthenticationToken)) {
			model.addAttribute("user", new User());
			return "index";
		} else {
			String role = auth.getAuthorities().iterator().next()
					.getAuthority();
			if (role.equals("ROLE_ADMIN")) {
				model.addAttribute("isAdmin", true);
			} else if (role.equals("ROLE_USER")) {
				model.addAttribute("isUser", true);
			}
			model.addAttribute("isValidUser", true);
			return "home";
		}

	}

	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(value = "/successLogin", method = RequestMethod.GET)
	public String user(HttpServletRequest request, ModelMap map) {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				map.addAttribute("isAdmin", true);
			} else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
				map.addAttribute("isUser", true);
			}

		}
		return "home";
	}

	

	
}
