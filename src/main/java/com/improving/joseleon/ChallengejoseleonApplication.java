package com.improving.joseleon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.java.Log;

@Log
@SpringBootApplication
public class ChallengejoseleonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengejoseleonApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner insertToH2DB() {
		return args -> {
			try {
				Connection con = DriverManager.getConnection("jdbc:h2:mem:testdb","sa", "");
				Statement sta = con.createStatement();
				String insertQuery = "INSERT INTO TD_RESERVATIONS (NAME, TIME_) VALUES\r\n" + 
				"	('Jhon', '2021-02-28'),\r\n" + 
				"	('Peter', '2021-03-27'),\r\n" + 
				"	('Janeth', '2021-04-26'),\r\n" + 
				"	('Katy', '2021-05-25'),\r\n" + 
				"	('Jesie', '2021-06-24'),\r\n" + 
				"	('Karl', '2021-07-12'),\r\n" + 
				"	('Joe', '2021-07-13'),\r\n" + 
				"	('Caroline', '2021-07-14'),\r\n" + 
				"	('Richard', '2021-07-15'),\r\n" + 
				"	('Steve', '2021-07-16');";
				sta.executeUpdate(insertQuery);
				log.info("Table populated correctly");
			} catch (Exception e) {
				log.severe("Has been ocurred an error inserting into the table" + e.getMessage());
			}
		};
	}

}
