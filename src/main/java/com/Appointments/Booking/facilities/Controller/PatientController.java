package com.Appointments.Booking.facilities.Controller;

import com.Appointments.Booking.facilities.Payload.PatientDTO;
import com.Appointments.Booking.facilities.Service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    /*
    POST http://localhost:8080/patients
    {
  "name": "John Doe",
  "contactNumber": "1234567890",
  "location": "New York"
}
     */

    //http://localhost:8080/patients
    @PostMapping
    public ResponseEntity<?> savePatient(@Valid  @RequestBody PatientDTO patientDTO , BindingResult bindingResult ) {

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


        PatientDTO savedPatientDTO = patientService.savePatient(patientDTO);
        return new ResponseEntity<>(savedPatientDTO,HttpStatus.CREATED);
    }
    //http://localhost:8080/patients?pageNo=0&pageSize=2
    @GetMapping
    public List<PatientDTO> collectAllPatientList(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        List<PatientDTO> collect = patientService.collectAllPatientList(pageNo,pageSize,sortBy,sortDir);
        return collect;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getByPatientId(@PathVariable("id")long id){
        PatientDTO patient = patientService.getByPatientId(id);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updateByPatientId(@PathVariable("id")long id , @RequestBody PatientDTO patientDTO){
        PatientDTO save = patientService.updateByPatientId(id, patientDTO);
        return new ResponseEntity<>(save,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByPatientId(@PathVariable("id")long id ){
        patientService.deleteByPatientId(id);
        return new ResponseEntity<>("sucessfully..... your id deleted",HttpStatus.OK);
    }

}