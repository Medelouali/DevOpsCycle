package com.ensa.tests.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationDto {
    private String targetId;
    private Double amount;
}
