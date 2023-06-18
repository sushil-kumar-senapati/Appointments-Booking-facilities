package com.Appointments.Booking.facilities.Repository;

import com.Appointments.Booking.facilities.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Add custom query methods or use the inherited methods from JpaRepository
}

