package com.improving.joseleon.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import lombok.extern.java.Log;

@Log
public class CRUD {

	public int insertIntoDB(Reservation res) {
		int result = 0;
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
			Statement sta = con.createStatement();
			String insertQuery = "INSERT INTO TD_RESERVATIONS (NAME, TIME_) VALUES\r\n" + 
			"('" + res.getName() + "', '" + res.getTime() + "');";
			result = sta.executeUpdate(insertQuery);
			log.info(result + " rows affected");
		} catch (SQLException e) {
			log.severe("DB Error: " + e.getMessage());
		}
		return result;
	};

	public Reservation getFromDB(int id) {
		Reservation output = new Reservation();
		List<Reservation> result = new ArrayList<>();

		try {
			Connection con = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
			String selectQuery = "SELECT ID, NAME, TIME_ FROM TD_RESERVATIONS WHERE ID = " + id;
			new JdbcTemplate(new SingleConnectionDataSource(con, true))
					.query(selectQuery,
							(rs, rowNum) -> new Reservation(rs.getInt("ID"), rs.getString("NAME"),
									new java.sql.Timestamp(rs.getDate("TIME_").getTime()).toLocalDateTime()))
					.forEach(result::add);
			log.info("Table queried correctly");
			if (result.size() > 0) {
				output = result.get(0);
			}
		} catch (SQLException e) {
			log.severe("DB Error: " + e.getMessage());
		}
		return output;
	}

	public int updateIntoDB(Reservation res) {
		int result = 0;
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
			Statement sta = con.createStatement();
			String updateQuery = "UPDATE TD_RESERVATIONS SET NAME = '" + res.getName() + "' AND TIME_ = '" + res.getTime() + "'"
					+ "WHERE ID = " + res.getId() + ";";
			result = sta.executeUpdate(updateQuery);
			log.info(result + " rows affected");
		} catch (SQLException e) {
			log.severe("DB Error: " + e.getMessage());
		}
		return result;
	};

	public int deleteFromDB(int id) {
		int result = 0;
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
			Statement sta = con.createStatement();
			String deleteQuery = "DELETE FROM TD_RESERVATIONS "
					+ "WHERE ID = " + id + ";";
			result = sta.executeUpdate(deleteQuery);
			log.info(result + " rows affected");
		} catch (SQLException e) {
			log.severe("DB Error: " + e.getMessage());
		}
		return result;
	};

	public List<Reservation> getAllFromDB() {
		List<Reservation> result = new ArrayList<>();

		try {
			Connection con = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
			String selectQuery = "SELECT ID, NAME, TIME_ FROM TD_RESERVATIONS";
			new JdbcTemplate(new SingleConnectionDataSource(con, true))
					.query(selectQuery,
							(rs, rowNum) -> new Reservation(rs.getInt("ID"), rs.getString("NAME"),
									new java.sql.Timestamp(rs.getDate("TIME_").getTime()).toLocalDateTime()))
					.forEach(result::add);
			log.info("Table queried correctly");
		} catch (SQLException e) {
			log.severe("DB Error: " + e.getMessage());
		}

		return result;
	};

}
