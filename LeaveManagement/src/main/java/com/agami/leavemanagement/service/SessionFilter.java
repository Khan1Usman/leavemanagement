package com.agami.leavemanagement.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Service;

@Service("sessionFilter")
public class SessionFilter implements InvalidSessionStrategy {

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	}

}
