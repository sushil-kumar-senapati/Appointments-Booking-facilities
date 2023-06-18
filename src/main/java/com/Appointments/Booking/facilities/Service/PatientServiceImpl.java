package com.Appointments.Booking.facilities.Service;

import com.Appointments.Booking.facilities.Entities.Patient;
import com.Appointments.Booking.facilities.Exception.ResourceNotFoundException;
import com.Appointments.Booking.facilities.Payload.PatientDTO;
import com.Appointments.Booking.facilities.Repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository,ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public PatientDTO savePatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setContactNumber(patientDTO.getContactNumber());
        patient.setLocation(patientDTO.getLocation());

        Patient pat = patientRepository.save(patient);

        // Convert the saved patient entity to PatientDTO
        PatientDTO savedPatientDTO = new PatientDTO();
        savedPatientDTO.setId(pat.getId());
        savedPatientDTO.setName(pat.getName());
        savedPatientDTO.setContactNumber(pat.getContactNumber());
        savedPatientDTO.setLocation(pat.getLocation());

        return savedPatientDTO;
    }

    @Override
    public List<PatientDTO> collectAllPatientList(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize ,sort);



        Page<Patient> all = patientRepository.findAll(pageable);

        List<Patient> content = all.getContent();

        List<PatientDTO> collect = content.stream().map(patient -> mapToDto(patient)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PatientDTO getByPatientId(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("patient id not found on this id" + id));

        return mapToDto(patient);
    }

    @Override
    public PatientDTO updateByPatientId(long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("patient id not found on this id" + id));
        Patient setNew = mapToEntity(patientDTO);
        setNew.setId(id);

        Patient save = patientRepository.save(setNew);

        return mapToDto(save);
    }

    @Override
    public void deleteByPatientId(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("patient id not found on this id" + id));

        patientRepository.deleteById(id);
    }

    PatientDTO mapToDto(Patient patient){
        PatientDTO pd= modelMapper.map(patient,PatientDTO.class);
        return pd;
    }
    Patient mapToEntity(PatientDTO patientDTO){
       Patient pn=modelMapper.map(patientDTO,Patient.class);
       return pn;
    }
}



