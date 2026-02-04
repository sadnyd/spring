package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.dto.TeacherDTO;
import com.example.entity.Student;
import com.example.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Analytics", description = "Student and Teacher Analytics API")
@SecurityRequirement(name = "basicAuth")
public class AnalyticsController {

    @Autowired
    private StudentService studentService;

    /**
     * Fetch all students in a department
     */
    @GetMapping("/students/department/{department}")
    @PreAuthorize("hasRole('ENGINEER')")
    @Operation(summary = "Get students by department", description = "Fetch all students belonging to a specific department")
    public ResponseEntity<List<StudentDTO>> getStudentsByDepartment(
            @PathVariable String department) {
        List<StudentDTO> students = studentService.findStudentsByDepartment(department);
        return ResponseEntity.ok(students);
    }

    /**
     * Calculate average mark for a department
     */
    @GetMapping("/students/department/{department}/average-mark")
    @PreAuthorize("hasRole('ENGINEER')")
    @Operation(summary = "Get average mark by department", description = "Calculate the average mark of students in a specific department")
    public ResponseEntity<BigDecimal> getAverageMarkByDepartment(
            @PathVariable String department) {
        BigDecimal averageMark = studentService.findAverageMarkByDepartment(department);
        return ResponseEntity.ok(averageMark);
    }

    /**
     * Create teacher with students
     */
    @PostMapping("/teachers")
    @PreAuthorize("hasRole('ENGINEER')")
    @Operation(summary = "Create teacher with students", description = "Create a new teacher with associated students")
    public ResponseEntity<String> createTeacherWithStudents(
            @RequestBody TeacherDTO teacherDTO) {
        try {
            // Convert StudentDTOs to Students
            java.util.Set<Student> students = teacherDTO.getStudents().stream()
                    .map(dto -> {
                        Student student = new Student();
                        student.setRollNumber(dto.getRollNumber());
                        student.setStudentName(dto.getStudentName());
                        student.setEmail(dto.getEmail());
                        student.setMark(dto.getMark());
                        return student;
                    })
                    .collect(Collectors.toSet());

            studentService.createTeacherWithStudents(
                    teacherDTO.getTeacherId(),
                    teacherDTO.getTeacherName(),
                    teacherDTO.getDepartment(),
                    students);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Teacher created successfully with " + students.size() + " students");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating teacher: " + e.getMessage());
        }
    }
}
