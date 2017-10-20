package com.sehyeon.trainGood;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(User user) {
		String query = "INSERT INTO User(ID, PW, Username) VALUES(?, ?, ?)";
		return jdbcTemplate.update(query, user.getId(), user.getPw(), user.getUsername());
	}

	public User select(String userId, String userPw) {
		String query = "SELECT * FROM User WHERE (ID = ? AND PW = ?)";
		try {
			return jdbcTemplate.queryForObject(query, new Object[] { userId, userPw }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new User(rs.getString("_id"), rs.getString("ID"), rs.getString("PW"), rs.getString("Username"),
							rs.getInt("Point"));
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	public Integer inqueryPoint(String id) {
		String query = "SELECT * FROM User WHERE (_id = ?)";
		return jdbcTemplate.queryForObject(query, new Object[] { id }, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("Point");
			}
		});
	}

	public int increasePoint(String id) {
		Integer current = inqueryPoint(id);
		String query = "update User set Point=? where _id=?";
		return jdbcTemplate.update(query, current + 1, id);
	}

	public int decreasePoint(String id) {
		Integer current = inqueryPoint(id);
		String query = "update User set Point=? where _id=?";
		return jdbcTemplate.update(query, current - 1, id);
	}

}
