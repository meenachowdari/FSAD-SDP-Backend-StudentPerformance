package com.klef.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;   
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private StudentRegisterRepository studentRegisterRepository;
    
    @Autowired
    private StudentMarksRepository studentMarksRepository;
    
    @Autowired
    private StudentAssignmentRepository studentAssignmentRepository;
    
    @Autowired
    private StudentFeedbackRepository studentFeedbackRepository;
    

    @Override
    public String studentRegistration(Student s) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        s.setPassword(encoder.encode(s.getPassword()));
        s.setStatus("PENDING");
        studentRepository.save(s);
        return "Student Registered Waiting for Admin Approval";
    }

    @Override
    public Student verifyStudentLogin(String username, String pwd) {
        Student student = studentRepository.findByUsernameAndPassword(
            username == null ? null : username.trim(),
            pwd
        );

        if (student == null) {
            return null;
        }

        String status = student.getStatus() == null ? "" : student.getStatus().trim().toUpperCase();

        if (!"APPROVED".equals(status)) {
            throw new RuntimeException("Account not approved by admin");
        }

        return student;
    }

	@Override
	public List<Teacher> getAvailableTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public String registerTeacherForStudent(int studentId, int teacherId) 
	{
			
		Student student = studentRepository.findById(studentId).orElse(null);
		
		if (student == null)
		    throw new RuntimeException("Student not found");

		Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
		if (teacher == null)
		    throw new RuntimeException("Teacher not found");

		if (studentRegisterRepository.existsByStudentIdAndTeacherId(studentId, teacherId))
		    return "Already registered with this teacher";

		StudentRegister s = new StudentRegister();
		s.setStudentId(studentId);
		s.setTeacherId(teacherId);
		studentRegisterRepository.save(s);

		return "Teacher registration successful";
	}

	@Override
	public Object getAssignedTeacher(int studentId)
	{
		StudentRegister map = studentRegisterRepository
				.findTopByStudentIdOrderByMappedAtDesc(studentId)
				.orElseThrow(() -> new RuntimeException("No teacher assigned yet"));
		
		Teacher teacher = teacherRepository.findById(map.getTeacherId()).orElse(null);
		
		if (teacher == null)
		    throw new RuntimeException("Assigned teacher not found");

		java.util.Map<String, Object> t = new java.util.HashMap<>();
		
		t.put("teacherId", teacher.getId());
		t.put("teacherName", teacher.getName());
		t.put("email", teacher.getEmail());
		t.put("department", teacher.getDepartment());
		t.put("subjects", teacher.getSubjects());

		return t;
	}

	@Override
	public List<StudentMarks> viewStudentMarks(int studentId) {
	    return studentMarksRepository.findByStudentId(studentId);
	}

	@Override
	public List<StudentFeedback> viewStudentFeedback(int studentId) {
	    return studentFeedbackRepository.findByStudentId(studentId);
	}

	@Override
	public List<StudentAssignment> viewStudentAssignments(int studentId) {
	    return studentAssignmentRepository.findByStudentId(studentId);
	}
  
}