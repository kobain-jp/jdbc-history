package com.dojo.jdbchistory.domain.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.dojo.jdbchistory.domain.user.entity.User;

public class UserJdbcDaoSpDs extends JdbcDaoSupport implements IUserDao {

	@Override
	public List<User> findAll() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<User> userList = new ArrayList<User>();

		try {
			con = getConnection();
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
		} finally {
			releaseConnection(con);
		}
		return userList;

	}

	@Override
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public int create(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(long id, User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
