package com.Appointments.Booking.facilities.Service;

import com.Appointments.Booking.facilities.Entities.Appointment;
import com.Appointments.Booking.facilities.Entities.Doctor;
import com.Appointments.Booking.facilities.Entities.Patient;
import com.Appointments.Booking.facilities.Exception.ResourceNotFoundException;
import com.Appointments.Booking.facilities.Payload.AppointmentDTO;
import com.Appointments.Booking.facilities.Repository.AppointmentRepository;
import com.Appointments.Booking.facilities.Repository.DoctorRepository;
import com.Appointments.Booking.facilities.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;


    public AppointmentServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository,
                                  AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void bookAppointment(AppointmentDTO appointmentDTO) {
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setDateTime(appointmentDTO.getDateTime());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setPatientName(patient.getName());
        appointment.setDoctorName(doctor.getName());

        appointmentRepository.save(appointment);
    }


}
