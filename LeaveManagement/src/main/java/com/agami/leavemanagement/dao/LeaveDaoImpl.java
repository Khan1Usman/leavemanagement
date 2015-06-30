package com.agami.leavemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.agami.leavemanagement.model.LeavePolicy;
import com.mysql.jdbc.Statement;

@Repository
public class LeaveDaoImpl implements LeaveDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean storeLeavesType(final JSONArray jsonArray, final int year,
			final String leavesType) {
		boolean flag = false;
		final String qury = "insert into leave_years(leave_year) values(?)";
		String query = "insert into leave_types (leave_type,total_leaves,leaves_type,year_id) values(?,?,?,?)";
		
		try {
			int yearId = 0;
			try {
				yearId = jdbcTemplate.queryForObject(
						"select id from leave_years where leave_year='" + year
								+ "'", Integer.class);
			} catch (Exception e) {

			}

			if (yearId > 0) {

			} else {
				KeyHolder idHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(
							Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(qury,
								Statement.RETURN_GENERATED_KEYS);
						pst.setInt(1, year);
						return pst;
					}
				}, idHolder);
				yearId = idHolder.getKey().intValue();
			}
			final int id = yearId;
			jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement prs, int i)
						throws SQLException {
					prs.setString(1,
							jsonArray.getJSONObject(i).getString("leaveType"));
					prs.setInt(2,
							jsonArray.getJSONObject(i).getInt("totalLeaves"));
					prs.setString(3, leavesType);
					prs.setInt(4, id);
				}

				@Override
				public int getBatchSize() {
					return jsonArray.length();
				}
			});
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<LeavePolicy> getAllLeaveType(int year) {
		List<LeavePolicy> leavePolicyList = null;
		try {
			leavePolicyList = jdbcTemplate
					.query("SELECT * FROM leave_types where year_id=(select id from leave_years where leave_year="
							+ year + ");", new RowMapper<LeavePolicy>() {

						@Override
						public LeavePolicy mapRow(ResultSet rst, int i)
								throws SQLException {
							LeavePolicy leavePolicy = new LeavePolicy();
							leavePolicy.setId(rst.getInt("id"));
							leavePolicy.setLeaveName(rst
									.getString("leave_type"));
							leavePolicy.setLeaveType(rst.getString("leaves_type"));
							leavePolicy.setTotalLeave(rst
									.getInt("total_leaves"));
							leavePolicy.setYear(rst.getInt("year_id"));
							return leavePolicy;
						}

					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leavePolicyList;
	}

	@Override
	public List<LeavePolicy> delete(int id) {
		List<LeavePolicy> leavePolicyList = null;
		try {
			jdbcTemplate.update("delete  from leave_types where id=?", id);
			// leavePolicyList = getAllLeaveType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leavePolicyList;
	}

	@Override
	public boolean update(LeavePolicy leavePolicy) {
		boolean flag = false;
		try {
			jdbcTemplate
					.update("update leave_types set leave_type=?,total_leaves=? where id=?",
							leavePolicy.getLeaveName(),
							leavePolicy.getTotalLeave(), leavePolicy.getId());
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean updateLeavesType(JSONArray jsonArray, int year,
			String leavesType) {
		boolean flag=false;
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

}
