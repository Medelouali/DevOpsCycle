package com.ensa.tests.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDto {
    private String fromAccountId;
    private String toAccountId;
    private Double amount;
}
