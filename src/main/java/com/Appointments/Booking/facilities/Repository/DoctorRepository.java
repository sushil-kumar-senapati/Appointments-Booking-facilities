package com.Appointments.Booking.facilities.Repository;

import com.Appointments.Booking.facilities.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Add custom query methods or use the inherited methods from JpaRepository
}

