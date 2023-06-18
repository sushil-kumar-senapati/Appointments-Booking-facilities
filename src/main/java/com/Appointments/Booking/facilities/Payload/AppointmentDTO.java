package com.Appointments.Booking.facilities.Payload;

import com.Appointments.Booking.facilities.Entities.Doctor;
import com.Appointments.Booking.facilities.Entities.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private PatientDTO patient;
    private DoctorDTO doctor;
    private LocalDateTime dateTime;
    private String patientName;
    private String doctorName;
//    private Long patientId;
//    private Long doctorId;

    public Long getPatientId() {
        // Return the patient ID
        return patient.getId();
    }

    public Long getDoctorId() {
        // Return the doctor ID
        return doctor.getId();
    }


}


