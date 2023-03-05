package com.example.covid19app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="covidCase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CovidCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

