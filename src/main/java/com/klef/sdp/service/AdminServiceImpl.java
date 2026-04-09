package com.klef.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.sdp.entity.Admin;
import com.klef.sdp.entity.Student;
import com.klef.sdp.entity.Teacher;
import com.klef.sdp.repository.AdminRepository;
import com.klef.sdp.repository.StudentRepository;
import com.klef.sdp.repository.TeacherRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Admin verifyAdminLogin(String username, String password) 
	{
		return adminRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String addTeacher(Teacher t) {
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 t.setPassword(encoder.encode(t.getPassword()));
		teacherRepository.save(t);
		return "Teacher Added successfully";
	}

	@Override
	public String verifyStudentRegistration(int id, String status) {
		Student student = studentRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Student not found"));

		    student.setStatus(status);

		    studentRepository.save(student);

		    return "Student status updated";
	}

	@Override
	public List<Teacher> viewAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public List<Student> viewAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public String deleteTeacher(int id) {
		teacherRepository.deleteById(id);
		return "Teacher Deleted Successfully";
	}

	@Override
	public String deleteStudent(int id) {
		studentRepository.deleteById(id);
		return "Student Deleted Successfully";
	}

	
}