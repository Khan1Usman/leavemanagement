package com.agami.leavemanagement.dao;

import com.agami.leavemanagement.model.User;
import com.agami.leavemanagement.model.VerificationToken;

public interface UserDao {

	public User getUser(String userName);

	public boolean storeVarifaicationToken(VerificationToken verificationToken);

	public VerificationToken getVerificationToken(String token);

	public boolean savePassword(String password,String userName);
}
