package com.wasd.web.controller;

import com.wasd.web.model.vacancy.VacancyRequest;
import com.wasd.web.model.vacancy.VacancyResponse;
import com.wasd.web.service.VacancyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/")
    public List<VacancyResponse> findAll() {
        return vacancyService.findAll();
    }

    @GetMapping("/{id}")
    public VacancyResponse findById(@PathVariable Long id) {
        return vacancyService.findById(id);
    }
    
    @PatchMapping("/{id}")
    public VacancyResponse update(@PathVariable Long id, @RequestBody VacancyRequest request) {
        return vacancyService.update(id, request);
    }
    
    //TODO: Get authorized user and set as author
    @PostMapping("/")
    public VacancyResponse create(@RequestBody VacancyRequest request) {
        return vacancyService.create(request);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vacancyService.delete(id);
    }
}