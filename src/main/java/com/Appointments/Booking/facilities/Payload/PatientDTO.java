package com.Appointments.Booking.facilities.Payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PatientDTO {
    private Long id;
    @NotEmpty
    @Size(max = 20,min = 2,message = "name shoud be more than 2 and maximum 20")
    private String name;
    @NotEmpty
    @Size(max = 10,min = 10,message = "contact number shoud be 10")
    private String contactNumber;
    @NotEmpty
    @Size(max = 20,min = 2,message = "location  shoud be more than 2 and maximum 20")
    private String location;
}

