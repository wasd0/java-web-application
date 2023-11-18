package com.wasd.web.model.vacancy;

import com.wasd.web.model.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyResponse {
    private Long id;
    private String title;
    private String content;
    private ZonedDateTime creationTime;
    private UserResponse authorResponse;
}