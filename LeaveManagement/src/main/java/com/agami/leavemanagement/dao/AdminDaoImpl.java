package com.agami.leavemanagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.agami.leavemanagement.model.GroupDepartmentInfo;
import com.agami.leavemanagement.model.GroupDepartments;
import com.agami.leavemanagement.model.OrganizationDepartment;
import com.agami.leavemanagement.model.OrganizationSubDepartment;

@Repository
public class AdminDaoImpl implements AdminDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> saveLeaveSetupData(
			List<Map<String, String>> adminLeaveSetup, String year) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			String value[] = new String[4];
			for (Map<String, String> map1 : adminLeaveSetup) {
				int i = 0;
				for (Entry<String, String> entry : map1.entrySet()) {
					String key = entry.getKey();
					value[i] = entry.getValue();
					System.out.println(key + " : " + value[i]);
					i++;
				}
				jdbcTemplate
						.update("insert into admin_leave_setup (title, event_type, start_date, end_date, leave_setup_year) values (?, ?, ?, ?, ?)",
								new Object[] { value[0], value[1], value[2],
										value[3], year });
			}
			map.put("isInserted", true);
			map.put("eventsList", adminLeaveSetup);
			map.put("leaveYear", year);
		} catch (Exception ex) {
			map.put("isInserted", false);
			map.put("eventsList", "");
			map.put("leaveYear", "");
			ex.printStackTrace();
		}
		list.add(map);

		return list;

	}

	@Override
	public List<Map<String, Object>> getLeaveSetupList() {
		String sql = "Select * from admin_leave_setup";
		try {
			return jdbcTemplate.query(sql,
					new RowMapper<Map<String, Object>>() {
						@Override
						public Map<String, Object> mapRow(ResultSet rst,
								int arg1) throws SQLException {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", rst.getInt(1));
							map.put("title", rst.getString(2));
							map.put("type", rst.getString(3));
							map.put("startsAt", rst.getString(4));
							map.put("endsAt", rst.getString(5));
							map.put("leaveSetupYear", rst.getString(6));
							return map;
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> deleteLeaveSetup(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jdbcTemplate.update("delete from admin_leave_setup where id=?",
					new Object[] { id });
			map.put("isDeleted", true);
			return map;
		} catch (Exception ex) {

			ex.printStackTrace();
			map.put("isDeleted", false);
			return map;
		}
	}

	@Override
	public Map<String, Object> updateLeaveSetupInfo(
			Map<String, Object> eventData) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jdbcTemplate
					.update("update admin_leave_setup set title =?, event_type =?, start_date=?, end_date =?, leave_setup_year =? where id =? ",
							new Object[] { eventData.get("title"),
									eventData.get("type"),
									eventData.get("startsAt"),
									eventData.get("endsAt"),
									eventData.get("leaveSetupYear"),
									eventData.get("id") });
			map.put("isUpdated", true);
		} catch (Exception ex) {
			map.put("isUpdated", false);
		}
		return map;
	}

	// vashu start
	@Override
	public int saveDepartments(final List<OrganizationDepartment> departments,
			final String who) throws SQLException {
		System.out.println(departments.getClass());
		for (int i = 0; i < departments.size(); i++) {
			System.out.println(departments.get(i).getClass());
			// OrganizationDepartment od =
			// (OrganizationDepartment)departments.get(i);
			// System.out.println(od);
		}
		int[] count = null;
		String sql = "INSERT INTO agami_emp_leave.department_info(department_name,departmet_head_id,departmet_head_name,descriptions, who)VALUES(?,?,?,?,?)";
		try {

			count = jdbcTemplate.batchUpdate(sql,
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							try {
								System.out.println(departments.get(i));
								System.out.println(departments.get(i)
										.getDepartmentName());
								System.out.println(departments.get(i)
										.getManagerId());
								System.out.println(departments.get(i)
										.getManagerName());
								System.out.println(who);
								ps.setString(1, departments.get(i)
										.getDepartmentName());
								ps.setInt(2,
										(departments.get(i).getManagerId()));
								ps.setString(3, departments.get(i)
										.getManagerName());
								ps.setString(4, departments.get(i)
										.getDescription());
								ps.setString(5, who);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}

						@Override
						public int getBatchSize() {
							System.out.println("----------------------------"
									+ departments.size());
							return departments.size();

						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		System.out.println(count);
		System.out.println(count);

		for (int i = 0; i < count.length; i++) {
			System.out.println("i === " + count[i]);
		}
		return 1;
	}

	// vash end
	@Override
	public Map<String, Object> checkDeptName(String deptName) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();

			jdbcTemplate.query(
					"select department_name from department_info where department_name="
							+ "'" + deptName + "'", rowCountCallbackHandler);
			int count = rowCountCallbackHandler.getRowCount();
			if (count == 0) {
				map.put("isUniqueDeptName", true);
			} else {
				map.put("isUniqueDeptName", false);
			}

		} catch (Exception ex) {
			map.put("isUniqueDeptName", false);
			ex.printStackTrace();
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getDeptSetupList() {
		String sql = "Select * from department_info";
		try {
			return jdbcTemplate.query(sql,
					new RowMapper<Map<String, Object>>() {
						@Override
						public Map<String, Object> mapRow(ResultSet rst,
								int arg1) throws SQLException {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", rst.getInt(1));
							map.put("departmentName", rst.getString(2));
							map.put("managerId", rst.getString(3));
							map.put("managerName", rst.getString(4));
							map.put("description", rst.getString(5));
							map.put("who", rst.getString(6));
							return map;
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> deleteDeptLeaveSetup(String sql, int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jdbcTemplate.update(sql, new Object[] { id });
			map.put("isDeleted", true);
			return map;
		} catch (Exception ex) {

			ex.printStackTrace();
			map.put("isDeleted", false);
			return map;
		}
	}

	@Override
	public Map<String, Object> updateDeptLeaveSetupInfo(
			Map<String, Object> deptInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jdbcTemplate
					.update("update department_info set department_name =?, departmet_head_id =?, departmet_head_name=?, who =? where id =? ",
							new Object[] { deptInfo.get("title"),
									deptInfo.get("type"),
									deptInfo.get("startsAt"),
									deptInfo.get("endsAt"),
									deptInfo.get("leaveSetupYear"),
									deptInfo.get("id") });
			map.put("isUpdated", true);
		} catch (Exception ex) {
			map.put("isUpdated", false);
		}
		return map;
	}

	@Override
	public int saveSubDepartments(
			final List<OrganizationSubDepartment> subDepartments,
			final int departmentId, final String who) throws SQLException {
		int[] count = null;
		String sql = "INSERT INTO sub_department_info(sub_department_name,department_id,manager_id,leave_head_id, descriptions, who)VALUES(?,?,?,?,?,?);";
		try {

			count = jdbcTemplate.batchUpdate(sql,
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							OrganizationSubDepartment department = subDepartments
									.get(i);
							// TODO Auto-generated method stub
							System.out.println(department
									.getSubDepartmentName());
							System.out.println(department.getManagerId());
							System.out.println(department.getManagerName());
							System.out.println(who);
							ps.setString(1, department.getSubDepartmentName());
							ps.setInt(2, departmentId);
							ps.setInt(3, department.getManagerId());
							ps.setInt(4, department.getLeaveHeadId());
							ps.setString(5, department.getDescription());
							ps.setString(6, who);

						}

						@Override
						public int getBatchSize() {
							// TODO Auto-generated method stub
							return subDepartments.size();
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		System.out.println(count);
		System.out.println(count);

		for (int i = 0; i < count.length; i++) {
			System.out.println("i === " + count[i]);
		}
		return 1;
	}

	@Override
	public List<Map<String, Object>> subDepartmentList() {
		String sql = "Select * from sub_department_info";
		try {
			return jdbcTemplate.query(sql,
					new RowMapper<Map<String, Object>>() {
						@Override
						public Map<String, Object> mapRow(ResultSet rst,
								int arg1) throws SQLException {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", rst.getInt(1));
							map.put("subDepartmentName", rst.getString(2));
							map.put("departmentId", rst.getString(3));
							map.put("managerId", rst.getInt(4));
							map.put("leaveHeadId", rst.getString(5));
							map.put("description", rst.getString(6));
							map.put("who", rst.getString(7));
							return map;
						}
						/*
						 * ps.setString(1, department.getSubDepartmentName());
						 * ps.setInt(2, departmentId); ps.setInt(3,
						 * department.getManagerId()); ps.setInt(4,
						 * department.getLeaveHeadId()); ps.setString(5,
						 * department.getDescription()); ps.setString(6, who);
						 */
					});
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> deleteSubDepartment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getDepartmentName() {
		String sql = "select id, department_name as departmentName, departmet_head_name as departmentHeadName from department_info";
		try {
			return jdbcTemplate.query(sql,
					new RowMapper<Map<String, Object>>() {
						@Override
						public Map<String, Object> mapRow(ResultSet rst,
								int arg1) throws SQLException {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", rst.getInt(1));
							map.put("departmentName", rst.getString(2));
							map.put("departmentHeadName", rst.getString(3));
							System.out.println("-------------- " + map);
							return map;
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> saveGroupInfo(
			GroupDepartmentInfo groupDepartmentInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			for (int i = 0; i < groupDepartmentInfo.getGroupDepartments()
					.size(); i++) {
				GroupDepartments groupDepartments = groupDepartmentInfo
						.getGroupDepartments().get(i);
				System.out.println("-- " + groupDepartments.getDescription());
				System.out.println("---- " + groupDepartments.getGroupName());
				System.out.println("---" + groupDepartments.getLeaveHeadId());
				System.out.println("--- " + groupDepartments.getTeamLeadName());
				jdbcTemplate
						.update("insert into group_info (group_name, team_lead_name, description, leave_head_id, deptartment_id, sub_department_id, group_manager_id) values(?, ?, ?, ?, ?, ?, ?)",
								new Object[] { groupDepartments.getGroupName(),
										groupDepartments.getTeamLeadName(),
										groupDepartments.getDescription(),
										groupDepartments.getLeaveHeadId(), 
										groupDepartmentInfo.getDepartmentId(),
										groupDepartmentInfo.getSubDepartmentId(),
										groupDepartmentInfo.getGroupManagerId()
										});
				map.put("isInserted", true);
			}
		} catch (Exception ex) {
			map.put("isInserted", false);
			ex.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> checkGroupName(String groupName, int deptId) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();

			jdbcTemplate.query(
					"select group_name from group_info where group_name=" + "'"
							+ groupName + "' and deptartment_id ='" + deptId
							+ "'", rowCountCallbackHandler);
			int count = rowCountCallbackHandler.getRowCount();
			if (count == 0) {
				map.put("isUniqueGroupName", true);
			} else {
				map.put("isUniqueGroupName", false);
			}

		} catch (Exception ex) {
			map.put("isUniqueGroupName", false);
			ex.printStackTrace();
		}
		return map;
	}
	@Override
	public Map<String, Object> groupList() {
		
		return null;
	}
	@Override
	public List<Map<String, Object>> getSubDepartmentName() {
		String sql = "select id, sub_department_name as subDepartmentName from sub_department_info";
		try {
			return jdbcTemplate.query(sql,
					new RowMapper<Map<String, Object>>() {
						@Override
						public Map<String, Object> mapRow(ResultSet rst,
								int arg1) throws SQLException {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", rst.getInt(1));
							map.put("subDepartmentName", rst.getString(2));
							System.out.println("-------------- " + map);
							return map;
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Map<String, Object>> getGroupName() {
		String sql = "select id, group_name as groupName from group_info";
		try {
			return jdbcTemplate.query(sql,
					new RowMapper<Map<String, Object>>() {
						@Override
						public Map<String, Object> mapRow(ResultSet rst,
								int arg1) throws SQLException {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", rst.getInt(1));
							map.put("groupName", rst.getString(2));
							return map;
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
