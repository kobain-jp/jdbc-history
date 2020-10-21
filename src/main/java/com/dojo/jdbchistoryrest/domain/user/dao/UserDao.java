package com.dojo.jdbchistoryrest.domain.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dojo.jdbchistoryrest.domain.user.entity.User;

public class UserDao implements IUserDao {

	private String driverClassName;
	private String url;
	private String userName;
	private String password;

	public UserDao(String driverClassName, String url, String userName, String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public List<User> findAll() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<User> userList = new ArrayList<User>();

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, userName, password);
			ps = con.prepareStatement("select * from user");
			rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setBirthDay(rs.getDate("birthday"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se2) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return userList;

	}

	@Override
	public Optional<User> findById(long id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, userName, password);
			ps = con.prepareStatement("select * from user where user_id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setBirthDay(rs.getDate("birthday"));
				
				return Optional.of(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se2) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return Optional.empty();
	}

	@Override
	public int create(User user) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);

			ps = con.prepareStatement("insert into user (user_name,birthDay) values (?,?)");
			ps.setString(1, user.getUserName());
			ps.setDate(2, user.getBirthDay());
			int createdCount = ps.executeUpdate();

			con.commit();

			return createdCount;

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int update(long id, User user) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement("update user set user_name=? ,birthday=? where user_id = ?");
			ps.setString(1, user.getUserName());
			ps.setDate(2, user.getBirthDay());
			ps.setLong(3, user.getUserId());
			int updatedCount = ps.executeUpdate();

			con.commit();

			return updatedCount;

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int delete(long id) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, userName, password);

			con.setAutoCommit(false);

			ps = con.prepareStatement("delete from user where user_id = ?");
			ps.setLong(1, id);
			int deletedCount = ps.executeUpdate();

			con.commit();

			return deletedCount;

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return 0;

	}

}
