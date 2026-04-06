package com.klef.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.klef.sdp.dto.StudentDetailsDTO;

import com.klef.sdp.entity.Student;
import com.klef.sdp.entity.StudentAssignment;
import com.klef.sdp.entity.StudentFeedback;
import com.klef.sdp.entity.StudentMarks;

import com.klef.sdp.entity.StudentRegister;
import com.klef.sdp.entity.Teacher;
import com.klef.sdp.repository.StudentAssignmentRepository;
import com.klef.sdp.repository.StudentFeedbackRepository;
import com.klef.sdp.repository.StudentMarksRepository;
import com.klef.sdp.repository.StudentRegisterRepository;
import com.klef.sdp.repository.StudentRepository;
import com.klef.sdp.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentRegisterRepository studentRegisterRepository;
	
	@Autowired
	private StudentMarksRepository studentMarksRepository;
	
	@Autowired
	private StudentAssignmentRepository studentAssignmentRepository
	;
	@Autowired
	private StudentFeedbackRepository studentFeedbackRepository;
	
	
	
	@Override
	public Teacher verifyTeacherLogin(String username, String pwd) 
	{
		return teacherRepository.findByUsernameAndPassword(username,pwd);
	}

	@Override
	public List<StudentDetailsDTO> viewAssignedStudents(int teacherId) {
	    List<StudentRegister> mappings = studentRegisterRepository.findByTeacherId(teacherId);

	    return mappings.stream()
	        .map(m -> {
	            Student s = studentRepository.findById(m.getStudentId()).orElse(null);
	            if (s == null) return null;

	            Teacher t = teacherRepository.findById(m.getTeacherId()).orElse(null);

	            StudentDetailsDTO dto = new StudentDetailsDTO();
	            dto.setId(s.getId());
	            dto.setName(s.getName());
	            dto.setEmail(s.getEmail());
	            dto.setUsername(s.getUsername());
	            dto.setSection(t != null ? t.getAssignedSection() : "-"); // section from teacher
	            return dto;
	        })
	        .filter(java.util.Objects::nonNull)
	        .collect(java.util.stream.Collectors.toList());
	}

	@Override
	public String addMarks(StudentMarks marks)
	{
	    studentMarksRepository.save(marks);
	    return "Marks Added Successfully";
	}

	@Override
	public String addFeedback(StudentFeedback feedback)
	{
	    studentFeedbackRepository.save(feedback);
	    return "Feedback Added Successfully";
	}

	@Override
	public String addAssignment(StudentAssignment assignment)
	{
	    studentAssignmentRepository.save(assignment);
	    return "Assignment Added Successfully";
	}

	@Override
	public long getTotalStudentsCount(int teacherId) {
	    return studentRegisterRepository.countByTeacherId(teacherId);
	}

	@Override
	public long getTestsGivenCount(int teacherId)
	{
	    List<StudentRegister> mappings = studentRegisterRepository.findByTeacherId(teacherId);
	    List<Integer> studentIds = mappings.stream()
	            .map(StudentRegister::getStudentId)
	            .distinct()
	            .collect(java.util.stream.Collectors.toList());

	    if (studentIds.isEmpty()) return 0;
	    return studentMarksRepository.countByStudentIdIn(studentIds);
	}

	@Override
	public double getAverageScore(int teacherId) {
	    List<StudentRegister> mappings = studentRegisterRepository.findByTeacherId(teacherId);
	    List<Integer> studentIds = mappings.stream()
	            .map(StudentRegister::getStudentId)
	            .distinct()
	            .collect(java.util.stream.Collectors.toList());

	    if (studentIds.isEmpty()) return 0.0;

	    Double avg = studentMarksRepository.getAverageMarksByStudentIds(studentIds);
	    return avg != null ? avg : 0.0;
	}

	



}
