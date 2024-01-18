package com.ensa.tests.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private String username;
    private Integer age;
    private Boolean isActive;
    private String email;
}
