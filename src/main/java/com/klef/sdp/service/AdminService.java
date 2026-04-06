package com.klef.sdp.service;

import java.util.List;

import com.klef.sdp.entity.Admin;
import com.klef.sdp.entity.Student;
import com.klef.sdp.entity.Teacher;

public interface AdminService {

	public Admin verifyAdminLogin(String username,String password);
	
	public String addTeacher(Teacher teacher);
	public List<Teacher> viewAllTeachers();
	public String deleteTeacher(int id);
	
	public List<Student> viewAllStudents();
	public String verifyStudentRegistration(int id, String status);
	public String deleteStudent(int id);
	
}
