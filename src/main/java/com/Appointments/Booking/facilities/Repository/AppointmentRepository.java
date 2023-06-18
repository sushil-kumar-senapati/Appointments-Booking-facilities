package com.Appointments.Booking.facilities.Repository;

import com.Appointments.Booking.facilities.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientName(String patientName);

    List<Appointment> findByDoctorName(String doctorName);

    // Other custom query methods if needed
}


