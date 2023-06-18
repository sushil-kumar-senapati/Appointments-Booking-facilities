package com.Appointments.Booking.facilities.Controller;

import com.Appointments.Booking.facilities.Entities.Doctor;
import com.Appointments.Booking.facilities.Payload.DoctorDTO;
import com.Appointments.Booking.facilities.Service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    /*
    POST http://localhost:8080/doctors
  {
  "name": "Dr. Jane Smith",
  "specialization": "Cardiology",
  "contactNumber": "9876543210"
}

     */

    //http://localhost:8080/doctors
    @PostMapping
    public ResponseEntity<Doctor> saveDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor savedDoctor = doctorService.saveDoctor(doctorDTO);
        return ResponseEntity.ok(savedDoctor);
    }
    @GetMapping
    public List<DoctorDTO> collectAllDoctorList(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        List<DoctorDTO> collect = doctorService.collectAllDoctorList(pageNo,pageSize,sortBy,sortDir);
        return collect;
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> collectByDoctorId(@PathVariable("id")long id){
        DoctorDTO doctor = doctorService.collectBydoctorId(id);
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateByDoctorId(@PathVariable("id")long id ,@RequestBody DoctorDTO doctorDTO){
        DoctorDTO save = doctorService.updateByDoctorId(id, doctorDTO);
        return new ResponseEntity<>(save,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByDoctorId(@PathVariable("id")long id){
        doctorService.deleteByDoctorId(id);
        return new ResponseEntity<>("sucessfully your id will be deleted",HttpStatus.OK);
    }
}
