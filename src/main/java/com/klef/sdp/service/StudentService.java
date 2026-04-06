package com.klef.sdp.service;

import java.util.List;

import com.klef.sdp.entity.Student;
import com.klef.sdp.entity.StudentAssignment;
import com.klef.sdp.entity.StudentFeedback;
import com.klef.sdp.entity.StudentMarks;
import com.klef.sdp.entity.Teacher;

public interface StudentService {
	
	public String studentRegistration(Student s);
	public Student verifyStudentLogin(String username,String pwd);
	
	List<Teacher> getAvailableTeachers();
	String registerTeacherForStudent(int studentId, int teacherId);
	Object getAssignedTeacher(int studentId);
	
	List<StudentMarks> viewStudentMarks(int studentId);
	List<StudentFeedback> viewStudentFeedback(int studentId);
	List<StudentAssignment> viewStudentAssignments(int studentId);
}
