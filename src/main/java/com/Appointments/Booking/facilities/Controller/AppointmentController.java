package com.Appointments.Booking.facilities.Controller;

import com.Appointments.Booking.facilities.Payload.AppointmentDTO;
import com.Appointments.Booking.facilities.Service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    /*
    POST http://localhost:8080/appointments
    {
  "patient": {
    "id": 3
  },
  "doctor": {
    "id": 3
  },
  "dateTime": "2023-06-28T10:00:00",
  "patientName": "Ravi palei",
  "doctorName": "Dr. Man"
}

     */

    @PostMapping
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.bookAppointment(appointmentDTO);
        return ResponseEntity.ok("Appointment booked successfully");
    }

}

