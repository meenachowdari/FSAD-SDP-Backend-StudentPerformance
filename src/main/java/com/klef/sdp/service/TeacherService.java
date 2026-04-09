package com.klef.sdp.service;

import java.util.List;


import com.klef.sdp.dto.StudentDetailsDTO;

import com.klef.sdp.entity.StudentAssignment;
import com.klef.sdp.entity.StudentFeedback;
import com.klef.sdp.entity.StudentMarks;
import com.klef.sdp.entity.Teacher;

public interface TeacherService
{
	
	public Teacher verifyTeacherLogin(String username,String pwd);
	
	public List<StudentDetailsDTO> viewAssignedStudents(int teacherId);
	public String addMarks(StudentMarks marks);
	public String addFeedback(StudentFeedback feedback);
	public String addAssignment(StudentAssignment assignment);
	List<StudentAssignment> viewAssignmentsByTeacher(int teacherId);
	
	 long getTotalStudentsCount(int teacherId);

	 long getTestsGivenCount(int teacherId);

	 double getPassPercentage(int teacherId);
}
