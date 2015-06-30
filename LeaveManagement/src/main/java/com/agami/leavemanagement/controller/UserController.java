package com.agami.leavemanagement.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.agami.leavemanagement.dao.UserDao;
import com.agami.leavemanagement.model.User;
import com.agami.leavemanagement.model.VerificationToken;
import com.agami.leavemanagement.service.UserService;

@Controller
public class UserController {
	@Autowired
	JavaMailSenderImpl mailSender;
	@Autowired
	UserDao userDao;
	@Autowired
	UserService userService;
	@Autowired
	StandardPasswordEncoder encoder;

	@RequestMapping(value = "/exceededSession", method = RequestMethod.GET)
	public String exceededSession(ModelMap model) {
		System.out
				.println("sadfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
		model.addAttribute("user", new User());
		return "index";
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/successLogout", method = RequestMethod.GET)
	public String logout(ModelMap map) {
		map.addAttribute("user", new User());
		map.addAttribute("message", "Successfully Logedout");
		return "index";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(value = "/error")
	public String error() {
		return "error";
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@RequestMapping(value = "/invalidSession", method = RequestMethod.GET)
	public void checkSession(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		throw new ResourceNotFoundException();
	}

	@RequestMapping(value = "/handle404", method = RequestMethod.GET)
	public String handleError(ModelMap map) {
		map.addAttribute("user", new User());
		return "index";
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public String forgetPassword(ModelMap map) {
		map.addAttribute("isValidUser", false);
		return "home";
	}

	@RequestMapping(value = "/passwordRecovery", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> passwordRecovery(@RequestBody User user,
			HttpServletRequest request) throws MessagingException,
			NamingException {
		System.out.println("user name " + user.getUsername());
		String token = UUID.randomUUID().toString();
		Map<String, Boolean> responseMap = new HashMap<String, Boolean>();
		if (userService.createVarifactionToken(user.getUsername(), token)) {
			String context = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(user.getEmail());
			helper.setSubject("Forget Password");
			helper.setText(
					"<html><body><a href='"
							+ context
							+ "/resetPassword?token="
							+ token
							+ "'>Please click on this link to reset password</a></body></html>",
					true);
			mailSender.send(message);
			responseMap.put("iserror", false);
		} else {
			responseMap.put("iserror", true);
		}
		return responseMap;
	}

	@RequestMapping(value = "/isUser", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkUserRole(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String role = auth.getAuthorities().iterator().next().getAuthority();
		Map<String, Object> map = new HashMap<String, Object>();
		if (role.equals("ROLE_ADMIN")) {
			map.put("user", "admin");

		} else if (role.equals("ROLE_USER")) {
			map.put("user", "user");

		}
		return map;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPasswordForm(@RequestParam String token, ModelMap map) {
		VerificationToken verificationToken = userService
				.findVerificationToken(token);
		if (verificationToken == null) {
			System.out.println("error");
			map.addAttribute("user", new User());
			return "index";
		} else {
			Calendar cal = Calendar.getInstance();
			System.out.println(verificationToken.getExpiryDate().getTime());
			System.out.println(cal.getTime().getTime());
			System.out
					.println((verificationToken.getExpiryDate().getTime() - cal
							.getTime().getTime()));
			if ((verificationToken.getExpiryDate().getTime() - cal.getTime()
					.getTime()) <= 0) {
				System.out.println("Expired");
				map.addAttribute("user", new User());
				map.addAttribute("message", "Url has expired please regenrate");
				return "index";
			} else {
				map.addAttribute("username", verificationToken.getUserName());
				return "resetpasswordform";
			}
		}
	}

	@RequestMapping(value = "/restorePassword", method = RequestMethod.POST)
	public String restorePassword(HttpServletRequest request, ModelMap map) {
		String password = request.getParameter("password");
		String userName = request.getParameter("username");
		System.out.println(password);
		System.out.println(userName);
		userDao.savePassword(encoder.encode(password), userName);
		map.addAttribute("user", new User());
		return "index";
	}
}
