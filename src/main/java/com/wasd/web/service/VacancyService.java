package com.wasd.web.service;

import com.wasd.web.model.vacancy.VacancyRequest;
import com.wasd.web.model.vacancy.VacancyResponse;

import java.util.List;

public interface    VacancyService {
    List<VacancyResponse> findAll();
    List<VacancyResponse> findAllByAuthor(Long authorId);
    VacancyResponse findById(Long id);
    VacancyResponse create(VacancyRequest request);
    VacancyResponse update(Long id, VacancyRequest request);
    void delete(Long id);
}
