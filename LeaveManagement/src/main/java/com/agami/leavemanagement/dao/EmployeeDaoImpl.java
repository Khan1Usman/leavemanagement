package com.agami.leavemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agami.leavemanagement.helper.IdGeneratorHelper;
import com.agami.leavemanagement.model.DegreeInfo;
import com.agami.leavemanagement.model.Employee;
import com.agami.leavemanagement.model.LeaveRequest;
import com.agami.leavemanagement.model.SecondaryInfo;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	@Autowired
	SimpleJdbcCall simpleJdbcCall;
	@Autowired
	IdGeneratorHelper idGeneratorHelper;

	@Autowired
	public void setdataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// @Autowired
	// private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Transactional
	@Override
	public Map<String, Object> updateEmployeeInfo(Employee employee) {
		String sqlEmpInfo = "update employee_basic_info set first_name =?, middle_name =?, last_name =?, emp_pwd =?, email_id =?, dob = ?, gender =?, mobile = ?, permanent_address = ?, local_address = ?, alternate_mobile = ?, alternate_email = ?, profile_pic = ? where id =?";
		String secEduSql = "insert into board_edu__info (emp_id,secondary_name, board_name, secondary_year, secondary_percentage) values (?, ?, ?, ?, ?)";
		String degreeEduSql = "insert into university_edu_info (emp_id, degree_name, university_name, percentage_marks, degree_year) values(?, ? , ?, ?, ?)";
		Map<String, Object> map = new HashMap<String, Object>();
		String dob = "";
		try {

			dob = employee.getBirthDay() + "-" + employee.getBirthdayMonth()
					+ "-" + employee.getBirthdayYear();
			jdbcTemplate.update(
					sqlEmpInfo,
					new Object[] { employee.getFirstName(),
							employee.getMiddleName(), employee.getLastName(),
							employee.getPassword(), employee.getEmail(), dob,
							employee.getMobileNumber(), employee.getGender(),
							employee.getParmanentAddress(),
							employee.getLocalAddress(),
							employee.getAlternateMobile(),
							employee.getAlternetEmail(),
							employee.getProfilePic(), 1 });

			for (int i = 0; i < employee.getSecondaryInfo().size(); i++) {
				SecondaryInfo secondaryInfo = (SecondaryInfo) employee
						.getSecondaryInfo().get(i);
				jdbcTemplate.update(
						secEduSql,
						new Object[] { 1, secondaryInfo.getSecondaryName(),
								secondaryInfo.getBoardName(),
								secondaryInfo.getSecondaryYear(),
								secondaryInfo.getSecondaryPercentage() });
			}
			for (int i = 0; i < employee.getDegreeInfo().size(); i++) {
				DegreeInfo degreeInfo = (DegreeInfo) employee.getDegreeInfo()
						.get(i);
				jdbcTemplate.update(
						degreeEduSql,
						new Object[] { 1, degreeInfo.getDegree(),
								degreeInfo.getUniversity(),
								degreeInfo.getPercentageMarks(),
								degreeInfo.getDegreeYear() });
			}

			// String sqlEmpInfo =
			// "update employee_basic_info set first_name = :firstName, middle_name = :middleName, last_name = :lastName, emp_pwd = :password, email_id = :email where id = :id";
			//
			// int id = 1;
			// String sql = "select univ_name from university_names ";
			// // SqlParameterSource parameterSource = new
			// MapSqlParameterSource();
			// MapSqlParameterSource mapSqlParameterSource = new
			// MapSqlParameterSource();
			// mapSqlParameterSource.addValue("firstName",
			// employee.getFirstName());
			// mapSqlParameterSource.addValue("middleName",
			// employee.getMiddleName());
			// mapSqlParameterSource.addValue("lastName",employee.getLastName());
			// mapSqlParameterSource.addValue("password",employee.getPassword());
			// mapSqlParameterSource.addValue("email", employee.getEmail());
			// mapSqlParameterSource.addValue("id", 1);
			// namedParameterJdbcTemplate.update(sqlEmpInfo,
			// mapSqlParameterSource);
			// //System.out.println("---------------"+ list);
			map.put("isUpdated", true);
			map.put("records", employee);
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			System.out.println();
			map.put("isUpdated", false);
		}
		return map;
	}

	@Override
	public Map<String, Object> employeeList(int limit, int offset,
			boolean isFirstSearched) {
		List<Employee> empList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		String empSql = "";
		String countSql = "";
		int totalResultsCount = 0;

		try {
			RowMapper<Employee> rowMapper = new RowMapper<Employee>() {

				@Override
				public Employee mapRow(ResultSet rst, int arg1)
						throws SQLException {
					Employee emp = new Employee();

					return emp;
				}
			};
			empList = jdbcTemplate.query(empSql, rowMapper);

		} catch (Exception ex) {

		}
		if (isFirstSearched) {
			totalResultsCount = jdbcTemplate.queryForObject(countSql,
					Integer.class);
		}
		map.put("empList", empList);
		map.put("totalResultCount", totalResultsCount);
		return map;
	}

	@Override
	public List<Employee> getIndividualEmployee(Integer id) {

		List<Employee> empList = null;
		String empSql = "";
		try {
			RowMapper<Employee> rowMapper = new RowMapper<Employee>() {

				@Override
				public Employee mapRow(ResultSet rst, int arg1)
						throws SQLException {
					Employee emp = new Employee();

					return emp;
				}
			};
			empList = jdbcTemplate
					.query(empSql, new Object[] { id }, rowMapper);

		} catch (Exception ex) {

		}
		return empList;
	}

	@Override
	public Map<String, Object> updateIndividualEmployeeInfo(Employee employee) {
		String updateSql = "update agami_emp_leave set ";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jdbcTemplate.update(updateSql, new Object[] {});
			map.put("isUpdated", true);
			return map;
		} catch (Exception ex) {
			map.put("isUpdated", false);
			return map;
		}

	}

	public void callStoreProcedure() {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("customerDetails");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("id", 1);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		System.out.println(simpleJdbcCallResult);
	}

	@Override
	public List<String> getUniversityName(String name, boolean flag) {
		List<String> list = null;
		try {
			if (!flag) {
				String sql = "select * from university_names where univ_name like '%"
						+ name + "%'";
				RowMapper<String> rowMapper = new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int arg1)
							throws SQLException {
						return rs.getString(2);
					}

				};
				list = jdbcTemplate.query(sql, rowMapper);
			} else {

				String sql = "select * from boards_name where board_name like '%"
						+ name + "%'";
				RowMapper<String> rowMapper = new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int arg1)
							throws SQLException {
						return rs.getString(2);
					}

				};
				list = jdbcTemplate.query(sql, rowMapper);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

	@Override
	public Map<String, Object> isUniqueEmail(String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();

			jdbcTemplate.query(
					"select email_id from employee_basic_info where email_id="
							+ "'" + email + "'", rowCountCallbackHandler);
			int count = rowCountCallbackHandler.getRowCount();
			if (count == 0) {
				map.put("isUniqueEmail", true);
			} else {
				map.put("isUniqueEmail", false);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> isUniqueMobileNumber(String mobileNumber) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();

			jdbcTemplate
					.query("select mobile from employee_basic_info where mobile="
							+ "'" + mobileNumber + "'", rowCountCallbackHandler);
			int count = rowCountCallbackHandler.getRowCount();
			if (count == 0) {
				map.put("isUniqueMobileNumber", true);
			} else {
				map.put("isUniqueMobileNumber", false);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	@Override
	public List<String> getSecurityQuestion() {
		List<String> list = null;
		String sql = "select question_name from security_questions";
		try {
			RowMapper<String> rowMapper = new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int arg1)
						throws SQLException {
					return rs.getString(1);
				}
			};
			list = jdbcTemplate.query(sql, rowMapper);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return list;
	}

	@Override
	public String save(Employee employee) {
		try {
			String sql = "INSERT INTO agami_emp_leave.employee_basic_info(emp_id,first_name,"
					+ "middle_name,last_name,email_id,gender,department_id,"
					+ "sub_department_id,group_id,designation,active,joining_date) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

			String emp_id = idGeneratorHelper.generateId();
			int updateCount = jdbcTemplate.update(
					sql,
					new Object[] { emp_id, employee.getFirstName(),
							employee.getMiddleName(), employee.getLastName(),
							employee.getEmail(), employee.getGender(),
							employee.getDepartmentId(),
							employee.getSubDepartmentId(),
							employee.getGroupId(), employee.getDesignation(),
							false, employee.getJoiningDate() });
			jdbcTemplate.update("insert into users (username,password) values(?,?)",
					emp_id, employee.getPassword());
			jdbcTemplate.update("insert into Authorities (username,authority) values(?,?)",
					emp_id, "ROLE_USER");
			// SimpleJdbcInsert simpleJdbcInsert = new
			// SimpleJdbcInsert(dataSource)
			// .withTableName("employee_basic_info");
			// Map<String, Object> map = new HashMap<String, Object>();
			// map.put("first_name", employee.getFirstName());
			// map.put("middle_name", employee.getMiddleName());
			// map.put("last_name", employee.getLastName());
			// map.put("emp_pwd", employee.getPassword());
			// map.put("email_id", employee.getEmail());
			// map.put("gender", employee.getGender());
			// map.put("salt", employee.getSalt());
			// // map.put("department_id", employee.getDepartmentId());
			// // map.put("sub_department_id", employee.getSubDepartmentId());
			// // map.put("group_id", employee.getGroupId());
			// map.put("designation", employee.getDesignation());
			// map.put("joining_date", employee.getJoiningDate());
			// String emp_id = idGeneratorHelper.generateId();
			// map.put("emp_id", emp_id);
			// map.put("active", false);
			// simpleJdbcInsert.execute(map);
			// map = null;
			System.out.println(updateCount);
			return emp_id;

		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}

	}

	@Override
	public int getCountPerDepartment(String Department) {
		int count = jdbcTemplate.queryForObject(
				"SELECT count(*) from employee_basic_info where department=?",
				Integer.class, Department);

		return count;
	}

	@Override
	public int getCount() {
		int count = jdbcTemplate.queryForObject(
				"SELECT count(*) from employee_basic_info", Integer.class);
		return count;
	}

	@Override
	public int saveLeaveRequest(final LeaveRequest leaveRequest) {
		final String sql = "INSERT into leave_request(user_id,title,type,starts_at,ends_at,status) values(?,?,?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, leaveRequest.getUserId());
				ps.setString(2, leaveRequest.getTitle());
				ps.setString(3, leaveRequest.getType());
				ps.setString(4, leaveRequest.getStartsAt());
				ps.setString(5, leaveRequest.getEndsAt());
				ps.setString(6, "PENDING");
				return ps;
			}
		}, holder);
		return holder.getKey().intValue();
	}

}
