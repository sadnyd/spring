package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private int rollNumber;
    private String studentName;
    private String email;
    private BigDecimal mark;
}
