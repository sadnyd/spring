package com.example.config;

import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create Computer Science Department
        Teacher cseTeacher = new Teacher();
        cseTeacher.setTeacherId(1);
        cseTeacher.setTeacherName("Dr. James Smith");
        cseTeacher.setDepartment("Computer Science");

        Set<Student> cseStudents = new HashSet<>();

        Student student1 = new Student();
        student1.setRollNumber(101);
        student1.setStudentName("Alice Johnson");
        student1.setEmail("alice@example.com");
        student1.setMark(new BigDecimal("85.50"));
        student1.setTeacher(cseTeacher);
        cseStudents.add(student1);

        Student student2 = new Student();
        student2.setRollNumber(102);
        student2.setStudentName("Bob Wilson");
        student2.setEmail("bob@example.com");
        student2.setMark(new BigDecimal("92.00"));
        student2.setTeacher(cseTeacher);
        cseStudents.add(student2);

        Student student3 = new Student();
        student3.setRollNumber(103);
        student3.setStudentName("Charlie Brown");
        student3.setEmail("charlie@example.com");
        student3.setMark(new BigDecimal("78.75"));
        student3.setTeacher(cseTeacher);
        cseStudents.add(student3);

        cseTeacher.setStudents(cseStudents);
        teacherRepository.save(cseTeacher);

        // Create Mechanical Engineering Department
        Teacher mechTeacher = new Teacher();
        mechTeacher.setTeacherId(2);
        mechTeacher.setTeacherName("Prof. Sarah Davis");
        mechTeacher.setDepartment("Mechanical Engineering");

        Set<Student> mechStudents = new HashSet<>();

        Student student4 = new Student();
        student4.setRollNumber(201);
        student4.setStudentName("Diana Prince");
        student4.setEmail("diana@example.com");
        student4.setMark(new BigDecimal("88.25"));
        student4.setTeacher(mechTeacher);
        mechStudents.add(student4);

        Student student5 = new Student();
        student5.setRollNumber(202);
        student5.setStudentName("Edward Norton");
        student5.setEmail("edward@example.com");
        student5.setMark(new BigDecimal("81.50"));
        student5.setTeacher(mechTeacher);
        mechStudents.add(student5);

        mechTeacher.setStudents(mechStudents);
        teacherRepository.save(mechTeacher);

        System.out.println("Database initialized with sample data!");
    }
}
