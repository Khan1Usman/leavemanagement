package com.agami.leavemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agami.leavemanagement.dao.UserDao;

import com.agami.leavemanagement.model.VerificationToken;

@Service
public class UserService {
	@Autowired
	UserDao userDao;

	public boolean createVarifactionToken(String userName, String token) {

		VerificationToken vToken = new VerificationToken(token, userName);
		return userDao.storeVarifaicationToken(vToken);

	}

	public VerificationToken findVerificationToken(String token) {
		return userDao.getVerificationToken(token);
	}
}
