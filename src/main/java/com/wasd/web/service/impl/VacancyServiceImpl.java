package com.wasd.web.service.impl;

import com.wasd.web.entity.User;
import com.wasd.web.entity.Vacancy;
import com.wasd.web.model.user.UserResponse;
import com.wasd.web.model.vacancy.VacancyRequest;
import com.wasd.web.model.vacancy.VacancyResponse;
import com.wasd.web.repository.VacancyRepository;
import com.wasd.web.service.UserService;
import com.wasd.web.service.VacancyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final UserService userService;

    public VacancyServiceImpl(VacancyRepository vacancyRepository, UserService userService) {
        this.vacancyRepository = vacancyRepository;
        this.userService = userService;
    }

    @Override
    public List<VacancyResponse> findAll() {
        return vacancyRepository.findAll()
                .stream()
                .map(this::buildResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public VacancyResponse findById(Long id) {
        return buildResponse(vacancyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public VacancyResponse create(VacancyRequest request) {
        Vacancy vacancy = createVacancyFromRequest(request);
        vacancyRepository.save(vacancy);
        return buildResponse(vacancy);
    }

    @Override
    public VacancyResponse update(Long id, VacancyRequest request) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        vacancy.setTitle(request.getTitle());
        vacancy.setContent(request.getContent());
        vacancyRepository.save(vacancy);
        return buildResponse(vacancy);
    }

    @Override
    public void delete(Long id) {
        vacancyRepository.deleteById(id);
    }

    private Vacancy createVacancyFromRequest(VacancyRequest request) {
        UserResponse userResponse = userService.findById(request.getAuthorId());
        User userFromBd = new User();
        userFromBd.setName(userResponse.getName());
        userFromBd.setId(userResponse.getId());
        userFromBd.setRegistrationDate(userResponse.getRegistrationDate());
        
        return Vacancy.builder()
                .author(userFromBd)
                .content(request.getContent())
                .title(request.getTitle())
                .creationTime(ZonedDateTime.now())
                .build();
    }

    private VacancyResponse buildResponse(Vacancy vacancy) {
        UserResponse authorResponse = userService.findById(vacancy.getAuthor().getId());

        return VacancyResponse.builder()
                .id(vacancy.getId())
                .title(vacancy.getTitle())
                .content(vacancy.getContent())
                .creationTime(vacancy.getCreationTime())
                .authorResponse(authorResponse)
                .build();
    }
}