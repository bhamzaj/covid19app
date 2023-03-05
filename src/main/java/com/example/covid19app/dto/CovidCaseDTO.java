package com.example.covid19app.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CovidCaseDTO {
    private int patientId;
    private String fullName;
    private Date dob;
    private String gender;
    private String city;
    private String hasSymptoms;
    private Boolean vaccinated;
    private String disease;
    private Date caseTimestamp;
}
