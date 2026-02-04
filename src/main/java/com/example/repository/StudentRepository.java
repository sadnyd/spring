package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.teacher.department = :department")
    List<Student> findStudentsByDepartment(@Param("department") String department);

    @Query("SELECT AVG(s.mark) FROM Student s WHERE s.teacher.department = :department")
    BigDecimal findAverageMarkByDepartment(@Param("department") String department);
}
