package com.Appointments.Booking.facilities.Service;

import com.Appointments.Booking.facilities.Entities.Doctor;
import com.Appointments.Booking.facilities.Payload.DoctorDTO;
import com.Appointments.Booking.facilities.Payload.PatientDTO;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(DoctorDTO doctorDTO);

    List<DoctorDTO> collectAllDoctorList(int pageNo, int pageSize, String sortBy, String sortDir);

    DoctorDTO collectBydoctorId(long id);

    DoctorDTO updateByDoctorId(long id, DoctorDTO doctorDTO);

    void deleteByDoctorId(long id);
}
