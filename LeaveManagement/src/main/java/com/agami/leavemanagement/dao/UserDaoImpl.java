package com.agami.leavemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.agami.leavemanagement.model.User;
import com.agami.leavemanagement.model.VerificationToken;

@Service
public class UserDaoImpl implements UserDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	WebApplicationContext context;

	@Override
	public User getUser(String userName) {
		User user = null;
		try {
			user = jdbcTemplate
					.queryForObject(
							"select user.*,auth.authority from users user left join  Authorities auth on user.username=auth.username where user.username='"
									+ userName + "' and enabled="+true, new RowMapper<User>() {
								@Override
								public User mapRow(ResultSet rst, int index)
										throws SQLException {
									User usr = (User) context.getBean("user");
									usr.setUsername(rst.getString("username"));
									usr.setEnabled(rst.getBoolean("enabled"));
									usr.setPassword(rst.getString("password"));
									usr.setAuthority(rst.getString("authority"));
									return usr;
								}
							});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean storeVarifaicationToken(VerificationToken verificationToken) {
		boolean flag = false;
		try {
			RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
			jdbcTemplate.query(
					"select id from varification_token where user_name='"
							+ verificationToken.getUserName() + "'",
					countCallback);
			int rowCount = countCallback.getRowCount();
			if (rowCount > 0) {
				jdbcTemplate
						.update("update varification_token set  token=?,expiry_date=? where user_name=?",
								verificationToken.getToken(),
								verificationToken.getExpiryDate(),
								verificationToken.getUserName());
			} else {

				jdbcTemplate
						.update("insert into varification_token (user_name,token,expiry_date) values(?,?,?)",
								verificationToken.getUserName(),
								verificationToken.getToken(),
								verificationToken.getExpiryDate());
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public VerificationToken getVerificationToken(final String token) {
		VerificationToken verificationToken = null;
		try {
			verificationToken = jdbcTemplate.queryForObject(
					"select * from varification_token where token='" + token
							+ "'", new RowMapper<VerificationToken>() {

						@Override
						public VerificationToken mapRow(ResultSet rst, int i)
								throws SQLException {
							VerificationToken vToken = (VerificationToken) context
									.getBean("verfiactionToken");
							vToken.setToken(token);
							vToken.setExpiryDate(rst.getDate("expiry_date"));
							vToken.setUserName(rst.getString("user_name"));
							return vToken;
						}

					});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return verificationToken;
	}

	@Override
	public boolean savePassword(String password,String userName) {
		boolean flag=false;
		try{
			jdbcTemplate.update("update users set password=? where username=?", password,userName);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

}
