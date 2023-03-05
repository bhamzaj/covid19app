package com.example.covid19app.services;

import com.example.covid19app.dto.CovidCaseDTO;
import com.example.covid19app.mappers.CovidCaseMapper;
import com.example.covid19app.models.CovidCase;
import com.example.covid19app.repositories.CovidCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CovidCaseServiceImpl implements ICovidCaseService{

    private final CovidCaseRepository covidCaseRepository;
    private final CovidCaseMapper mapper;

    @Override
    public CovidCase addNewCase(CovidCaseDTO covidCaseDTO) {
        CovidCase covidCase = mapper.map(covidCaseDTO);
        return covidCaseRepository.save(covidCase);
    }

    @Override
    public CovidCase updateCase(CovidCase covidCase) {
        return covidCaseRepository.save(covidCase);
    }

    @Override
    public void deleteCase(CovidCase covidCase) {
        covidCaseRepository.delete(covidCase);
    }

    @Override
    public CovidCase getCaseById(int patientId) {
        Optional<CovidCase> covidCase = covidCaseRepository.findById(patientId);
        if(covidCase.isPresent()) {
            return covidCase.get();
        }else{
            return null;
        }

    }

    @Override
    public List<CovidCase> getCases() {
       return covidCaseRepository.findAll();
    }

    public int casesCount(){
        return getCases().size();
    }

    public List<CovidCase> getCasesWithSymptoms(){
        return covidCaseRepository.casesWithSymptoms();
    }

    public List<CovidCase> getCasesVaccinated(){
        return covidCaseRepository.casesVaccinated();
    }
}
