package com.example.covid19app.repositories;

import com.example.covid19app.models.CovidCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CovidCaseRepository extends JpaRepository<CovidCase, Integer> {

    @Query("SELECT c FROM CovidCase c WHERE c.hasSymptoms = 'Yes'")
    List<CovidCase> casesWithSymptoms();

    @Query("SELECT c FROM CovidCase c WHERE c.vaccinated = 'true'")
    List<CovidCase> casesVaccinated();

}
