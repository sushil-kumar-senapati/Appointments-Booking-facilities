package com.Appointments.Booking.facilities.Service;

import com.Appointments.Booking.facilities.Entities.Doctor;
import com.Appointments.Booking.facilities.Entities.Patient;
import com.Appointments.Booking.facilities.Exception.ResourceNotFoundException;
import com.Appointments.Booking.facilities.Payload.DoctorDTO;
import com.Appointments.Booking.facilities.Payload.PatientDTO;
import com.Appointments.Booking.facilities.Repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private ModelMapper modelMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository,ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public Doctor saveDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setContactNumber(doctorDTO.getContactNumber());

        return doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorDTO> collectAllDoctorList(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize ,sort);



        Page<Doctor> all = doctorRepository.findAll(pageable);

        List<Doctor> content = all.getContent();

        List<DoctorDTO> collect = content.stream().map(patient -> mapToDto(patient)).collect(Collectors.toList());
        return collect;

    }

    @Override
    public DoctorDTO collectBydoctorId(long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("status not found on this id" + id));
        return mapToDto(doctor);
    }

    @Override
    public DoctorDTO updateByDoctorId(long id, DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("status not found on this id" + id));
        Doctor updoct = mapToEntity(doctorDTO);
        updoct.setId(id);

        Doctor save = doctorRepository.save(updoct);
        return mapToDto(save);
    }

    @Override
    public void deleteByDoctorId(long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("status not found on this id" + id));
        doctorRepository.deleteById(id);
    }


    DoctorDTO mapToDto(Doctor doctor){
      DoctorDTO d=modelMapper.map(doctor,DoctorDTO.class);
      return d;
    }

    Doctor mapToEntity(DoctorDTO doctorDTO){
        Doctor dd = modelMapper.map(doctorDTO, Doctor.class);
        return dd;
    }
}

