package com.example.covid19app.controllers;

import com.example.covid19app.dto.CovidCaseDTO;
import com.example.covid19app.models.CovidCase;
import com.example.covid19app.services.CovidCaseServiceImpl;
import com.example.covid19app.utils.AttributeNames;
import com.example.covid19app.utils.Mappings;
import com.example.covid19app.utils.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class CovidCaseController {

    @Autowired
    private CovidCaseServiceImpl covidCaseService;
    private final List<String> options = List.of("Yes", "No", "Unknown");

    @GetMapping
    public String homePage(Model model){
        model.addAttribute("casesCount", covidCaseService.casesCount());
        model.addAttribute("symptomsCount", covidCaseService.getCasesWithSymptoms().size());
        model.addAttribute("vaccinatedCount", covidCaseService.getCasesVaccinated().size());
        return ViewNames.HOMEPAGE;
    }

    @GetMapping(Mappings.REGISTER_CASE)
    public String showRegisterPage(Model model){
        CovidCase covidCase = new CovidCase();
        covidCase.setCaseTimestamp(new Date(System.currentTimeMillis()));
        model.addAttribute(AttributeNames.COVID_CASE, covidCase);
        model.addAttribute("diseaseOptions", options);

        return ViewNames.REGISTER_CASE;
    }

    @PostMapping(Mappings.REGISTER_CASE)
    public String registerCase(@ModelAttribute(AttributeNames.COVID_CASE) CovidCaseDTO covidCase, Model model){
        covidCase.setCaseTimestamp(new Date(System.currentTimeMillis()));
        String msg = "Patient Successfully registered with id: "+ covidCaseService.addNewCase(covidCase).getPatientId();
        model.addAttribute("msg", msg);
        model.addAttribute("diseaseOptions", options);
        return ViewNames.REGISTER_CASE;
    }

    @GetMapping(Mappings.CASES_LIST)
    public String showCasesList(Model model){
        model.addAttribute(AttributeNames.COVID_CASE_LIST,
                covidCaseService.getCases());
        return ViewNames.CASES_LIST;
    }

    @GetMapping(Mappings.EDIT_CASE)
    public String editCase(@RequestParam("id") int id, Model model){
        CovidCase covidCase = covidCaseService.getCaseById(id);
        if(covidCase == null || id < 1) {
            return "redirect:/"+ Mappings.CASES_LIST;

        }else{
            model.addAttribute(AttributeNames.COVID_CASE,covidCase);
            List<String> options = List.of("Yes", "No", "Unknown");
            model.addAttribute("diseaseOptions", options);
            return ViewNames.EDIT_CASE;
        }
    }

    @PostMapping(Mappings.UPDATE_CASE)
    public String updateCase(@ModelAttribute(AttributeNames.COVID_CASE) CovidCase covidCase, Model model){
        covidCaseService.updateCase(covidCase);
        return "redirect:/"+ Mappings.CASES_LIST;
    }

    @GetMapping(Mappings.FIND_BY_ID)
    public String findCaseById(@RequestParam("id") int id, Model model){
        CovidCase foundCovidCase = covidCaseService.getCaseById(id);
        if(foundCovidCase == null || id < 1) {
            model.addAttribute("notFound","Case not found in DB!");
            model.addAttribute("casesCount", covidCaseService.casesCount());
            model.addAttribute("symptomsCount", covidCaseService.getCasesWithSymptoms().size());
            model.addAttribute("vaccinatedCount", covidCaseService.getCasesVaccinated().size());
            return ViewNames.HOMEPAGE;

        }else{
            model.addAttribute(AttributeNames.COVID_CASE,foundCovidCase);
            return ViewNames.FIND_BY_ID;
        }

    }


    @RequestMapping(Mappings.DELETE_CASE)
    public String deleteCase(@RequestParam("id") int id){
        CovidCase covidCase = covidCaseService.getCaseById(id);
        if(covidCase == null || id < 1) {
            return "redirect:/" + Mappings.CASES_LIST;
        }else {
            covidCaseService.deleteCase(covidCase);
            return "redirect:/"+ Mappings.CASES_LIST;
        }
    }
}