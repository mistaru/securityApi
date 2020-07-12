package org.example.argen.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.argen.enums.Status;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class TodoFilterDto {
    private String title;
    private Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate from;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate to;

    public TodoFilterDto() {
    }
}
