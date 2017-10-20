package com.sehyeon.trainGood;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SitDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List inqueryOccupiedList(String trainnum) {
		String query = "SELECT * FROM Sit WHERE (TrainNumber = ?)";
		List<Map<String, Object>> response = jdbcTemplate.queryForList(query.replace("?", trainnum));
		return response;
	}

	public int select(String trainnum, String chairnum, String _id, String dest) {
		// TODO Auto-generated method stub

		List<Map<String, Object>> list = inqueryOccupiedList(trainnum);
		for (Map<String, Object> obj : list) {
			if (obj.get("OccupiedUser").toString().equals(_id)) {
				return -1;
			} else if (obj.get("ReservationUser") != null && obj.get("ReservationUser").toString().equals(_id)) {
				return -2;
			}
		}

		try {
			String query = "INSERT INTO Sit(TrainNumber, ChairNumber, OccupiedUser, Destination) VALUES(?, ?, ?, ?)";
			return jdbcTemplate.update(query, trainnum, chairnum, _id, dest);
		} catch (Exception e) {
			return -3;
		}
	}

	public Integer leave1(String TrainNum, String ChairNum) {
		String inq = "select * from sit where (TrainNumber = ? AND ChairNumber = ?)";
		try {
			return jdbcTemplate.queryForObject(inq, new Object[] { TrainNum, ChairNum }, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					String query = "delete from sit where _id = " + rs.getString("_id");
					return jdbcTemplate.update(query);
				}

			});
		} catch (Exception e) {
			return 0;
		}
	}

	public Integer leave2(String TrainNum, String ChairNum) {
		String inq = "select * from sit where (TrainNumber = ? AND ChairNumber = ?)";
		try {
			return jdbcTemplate.queryForObject(inq, new Object[] { TrainNum, ChairNum }, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					String query = "update sit set OccupiedUser = " + rs.getString("ReservationUser")
							+ ", ReservationUser = NULL where _id = " + rs.getString("_id");
					return jdbcTemplate.update(query);
				}

			});
		} catch (Exception e) {
			return 0;
		}
	}

	public Integer reserve(String TrainNum, String ChairNum, String _id) {
		String inq = "select * from sit where (TrainNumber = ? AND ChairNumber = ?)"; // 조회하는
																						// 애
		try {

			return jdbcTemplate.queryForObject(inq, new Object[] { TrainNum, ChairNum }, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					String query = "update sit set ReservationUser = " + _id + " where _id = " + rs.getString("_id");
					return jdbcTemplate.update(query);
				}

			});
		} catch (Exception e) {
			return 0;
		}
	}

	public Integer unreserve(String TrainNum, String ChairNum) {
		String inq = "select * from sit where (TrainNumber = ? AND ChairNumber = ?)"; // 조회하는
																						// 애
		try {

			return jdbcTemplate.queryForObject(inq, new Object[] { TrainNum, ChairNum }, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					String query = "update sit set ReservationUser = NULL" + " where _id = " + rs.getString("_id");
					return jdbcTemplate.update(query);
				}

			});
		} catch (Exception e) {
			return 0;
		}
	}

}
