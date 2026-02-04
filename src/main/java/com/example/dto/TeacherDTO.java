package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private Integer teacherId;
    private String teacherName;
    private String department;
    private Set<StudentDTO> students;
}
