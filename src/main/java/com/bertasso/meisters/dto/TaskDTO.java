package com.bertasso.meisters.dto;

import com.bertasso.meisters.domain.TaskStatus;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;

public record TaskDTO(String title,
                      String description,
                      TaskStatus status,
                      LocalDate dtcreation) {
}
