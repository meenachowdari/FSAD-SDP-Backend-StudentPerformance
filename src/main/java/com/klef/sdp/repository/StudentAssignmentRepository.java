package com.klef.sdp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.sdp.entity.StudentAssignment;
import com.klef.sdp.entity.Teacher;

public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Integer> 
{
    List<StudentAssignment> findByStudentId(int studentId);
    
    List<StudentAssignment> findByTeacherId(Teacher teacher);
}