package com.klef.sdp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.klef.sdp.entity.StudentMarks;

public interface StudentMarksRepository extends JpaRepository<StudentMarks, Integer> 
{
    List<StudentMarks> findByStudentId(int studentId);
    
    long countByStudentIdIn(List<Integer> studentIds);

    @Query("select avg(m.marks) from StudentMarks m where m.studentId in :studentIds")
    Double getAverageMarksByStudentIds(@Param("studentIds") List<Integer> studentIds);
}