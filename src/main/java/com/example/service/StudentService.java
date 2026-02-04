package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Find all students by department
     */
    public List<StudentDTO> findStudentsByDepartment(String department) {
        List<Student> students = studentRepository.findStudentsByDepartment(department);
        return students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Calculate average mark for a department
     */
    public BigDecimal findAverageMarkByDepartment(String department) {
        return studentRepository.findAverageMarkByDepartment(department);
    }

    /**
     * Create a teacher with students
     */
    public void createTeacherWithStudents(Integer teacherId, String teacherName, String department,
            Set<Student> students) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(teacherName);
        teacher.setDepartment(department);

        // Associate students with teacher
        if (students != null) {
            students.forEach(student -> student.setTeacher(teacher));
            teacher.setStudents(students);
        }

        teacherRepository.save(teacher);
    }

    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getRollNumber(),
                student.getStudentName(),
                student.getEmail(),
                student.getMark());
    }
}
