package com.klef.sdp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.sdp.entity.StudentFeedback;

public interface StudentFeedbackRepository extends JpaRepository<StudentFeedback, Integer> 
{
    List<StudentFeedback> findByStudentId(int studentId);
}