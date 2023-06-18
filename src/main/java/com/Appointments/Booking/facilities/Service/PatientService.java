package com.Appointments.Booking.facilities.Service;

import com.Appointments.Booking.facilities.Payload.PatientDTO;

import java.util.List;

public interface PatientService {
    PatientDTO savePatient(PatientDTO patientDTO);

    List<PatientDTO> collectAllPatientList(int pageNo, int pageSize, String sortBy, String sortDir);

    PatientDTO getByPatientId(long id);

    PatientDTO updateByPatientId(long id, PatientDTO patientDTO);

    void deleteByPatientId(long id);
}
