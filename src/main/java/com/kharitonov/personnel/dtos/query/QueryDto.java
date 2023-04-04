package com.kharitonov.personnel.dtos.query;

import com.kharitonov.personnel.data.models.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryDto {

    private Long Id;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private UserEntity fromUser;
    private UserEntity forUser;
}
