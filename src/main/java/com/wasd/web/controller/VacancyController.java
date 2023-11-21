package com.wasd.web.controller;

import com.wasd.web.model.vacancy.VacancyRequest;
import com.wasd.web.model.vacancy.VacancyResponse;
import com.wasd.web.service.VacancyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping
    public String findAll(Model model) {

        model.addAttribute("vacancies", vacancyService.findAll());

        return "vacancies/allVacancies";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        VacancyResponse vacancyResponse = vacancyService.findById(id);
        ZonedDateTime creationDateTime = vacancyResponse.getCreationTime();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(creationDateTime.toInstant(), ZoneId.systemDefault());

        String time = String.format("%s:%s", localDateTime.getHour(), creationDateTime.getMinute());
        String date = String.format("%s.%s", localDateTime.getDayOfMonth(), creationDateTime.getMonthValue());

        model.addAttribute("vacancy", vacancyResponse);
        model.addAttribute("creationTime", String.format("%s - %s", time, date));

        return "vacancies/vacancy";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        VacancyRequest vacancy = new VacancyRequest();
        model.addAttribute("vacancy", vacancy);
        
        return "vacancies/createVacancyForm";
    }
    
    @PatchMapping("/{id}")
    public VacancyResponse update(@PathVariable Long id, @RequestBody VacancyRequest request) {
        return vacancyService.update(id, request);
    }

    //TODO: Get authorized user and set as author
    @PostMapping("/created")
    public String create(@ModelAttribute VacancyRequest request, Model model) {
        model.addAttribute("vacancy", request);
        vacancyService.create(request);
        return "redirect:/vacancies";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vacancyService.delete(id);
    }
}