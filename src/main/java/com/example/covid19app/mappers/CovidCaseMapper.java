package com.example.covid19app.mappers;

import com.example.covid19app.dto.CovidCaseDTO;
import com.example.covid19app.models.CovidCase;
import org.springframework.stereotype.Component;

@Component
public class CovidCaseMapper {

    public CovidCase map(CovidCaseDTO covidCaseDTO){
        CovidCase covidCase = new CovidCase();
        covidCase.setPatientId(covidCaseDTO.getPatientId());
        return covidCase;
    }

    public CovidCaseDTO map(CovidCase covidCaseDTO){
        CovidCaseDTO covidCase = new CovidCaseDTO();
        covidCase.setPatientId(covidCaseDTO.getPatientId());
        return covidCase;
    }


}
