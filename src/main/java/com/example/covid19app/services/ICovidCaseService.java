package com.example.covid19app.services;

import com.example.covid19app.dto.CovidCaseDTO;
import com.example.covid19app.models.CovidCase;

import java.util.List;

public interface ICovidCaseService {
    CovidCase addNewCase(CovidCaseDTO covidCase);
    CovidCase updateCase(CovidCase covidCase);
    void deleteCase(CovidCase covidCase);
    CovidCase getCaseById(int patientId);
    List<CovidCase> getCases();


}
