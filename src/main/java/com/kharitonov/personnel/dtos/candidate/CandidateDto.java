package com.kharitonov.personnel.dtos.candidate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String profession;
    private String education;
    private LocalDate birthday;
    private String email;

}
