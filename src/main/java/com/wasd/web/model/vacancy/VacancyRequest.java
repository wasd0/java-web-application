package com.wasd.web.model.vacancy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyRequest {
    private Long id;
    private String title;
    private String content;
    private ZonedDateTime creationTime;
    private Long authorId;
}