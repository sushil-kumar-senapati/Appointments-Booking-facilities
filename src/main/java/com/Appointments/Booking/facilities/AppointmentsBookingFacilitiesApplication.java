package com.Appointments.Booking.facilities;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppointmentsBookingFacilitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentsBookingFacilitiesApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
