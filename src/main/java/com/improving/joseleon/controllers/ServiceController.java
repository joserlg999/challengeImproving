package com.improving.joseleon.controllers;

import java.util.List;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.improving.joseleon.data.CRUD;
import com.improving.joseleon.data.Reservation;

@RestController
public class ServiceController {
	
	CRUD crud = new CRUD();

	@GetMapping("/reservations")
	public List<Reservation> reservation() {
		List<Reservation> result = crud.getAllFromDB();
		return result;
	}
	
	@RestController
	public class MyErrorController implements ErrorController {

		private static final String PATH = "/error";

		@RequestMapping(value = PATH )
		public String myerror() {
			return "<center><h1><a href=\"/reservations\" th:href=\"@{/reservations}\">Wrong page, Go to Reservations Service</a></h1></center>";
		}

		public String getErrorPath() {
			return PATH;
		}
	}

}
