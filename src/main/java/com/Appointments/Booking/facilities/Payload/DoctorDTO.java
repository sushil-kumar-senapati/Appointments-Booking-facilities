package com.Appointments.Booking.facilities.Payload;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String name;
    private String specialization;
    private String contactNumber;

}
