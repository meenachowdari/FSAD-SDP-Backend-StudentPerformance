package com.klef.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.sdp.dto.StudentTeacherRegisterDTO;
import com.klef.sdp.entity.Student;
import com.klef.sdp.service.StudentService;

@RestController
@RequestMapping("studentapi")
@CrossOrigin("*")

public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	  public String studenthome()
	  {
		   return "Student Controller Demo";
	  }
	
	  @PostMapping("/register")
	    public ResponseEntity<String> registerStudent(@RequestBody Student student)
	    {
	        String output = studentService.studentRegistration(student);
	        return ResponseEntity.ok(output);
	    }
	  
	  @PostMapping("/login")
	  public ResponseEntity<?> verifystudentlogin(@RequestBody Student student) {
	      try {
	          Student s = studentService.verifyStudentLogin(student.getUsername(), student.getPassword());

	          if (s != null) {
	              return ResponseEntity.ok(s);
	          } else {
	              return ResponseEntity.status(401).body("Login Invalid");
	          }
	      } catch (RuntimeException e) {
	          
	          return ResponseEntity.status(403).body(e.getMessage());
	      } catch (Exception e) {
	          return ResponseEntity.status(500).body("Internal Server Error");
	      }
	  }
	  
	  @GetMapping("/teachers/available")
	  public ResponseEntity<?> getAvailableTeachers()
	  {
		  try
		  {
			  return ResponseEntity.ok(studentService.getAvailableTeachers());
		  }
		  catch (Exception e)
		  {
			  return ResponseEntity.status(500).body("Unable to load teachers");
		  }
	  }
	  
	  @PostMapping("/teachers/register")
	  public ResponseEntity<?> registerTeacher(@RequestBody StudentTeacherRegisterDTO request)
	  {
		  try
		  {
			  String output = studentService.registerTeacherForStudent(request.getStudentId(), request.getTeacherId());
			  return ResponseEntity.ok(output);
		  }
		  catch (RuntimeException e)
		  {
		  		return ResponseEntity.status(400).body(e.getMessage());
		  }
		  catch (Exception e)
		  {
		  		return ResponseEntity.status(500).body("Internal Server Error");
		  }
	  }
	
	  @GetMapping("/assigned-teacher/{studentId}")
	  public ResponseEntity<?> getAssignedTeacher(@PathVariable int studentId)
	  {
		  try
		  {
			  return ResponseEntity.ok(studentService.getAssignedTeacher(studentId));
		  }
		  catch (RuntimeException e)
		  {
			  return ResponseEntity.status(404).body(e.getMessage());
		  }
		  catch (Exception e)
		  {
			  return ResponseEntity.status(500).body("Internal Server Error");
		  }
	  }
	  
	  @GetMapping("/reports/{studentId}")
	  public ResponseEntity<?> viewReports(@PathVariable int studentId) {
	      try {
	          return ResponseEntity.ok(studentService.viewStudentMarks(studentId));
	      } catch (Exception e) {
	          return ResponseEntity.status(500).body("Unable to load marks");
	      }
	  }

	  @GetMapping("/suggestions/{studentId}")
	  public ResponseEntity<?> viewSuggestions(@PathVariable int studentId) {
	      try {
	          return ResponseEntity.ok(studentService.viewStudentFeedback(studentId));
	      } catch (Exception e) {
	          return ResponseEntity.status(500).body("Unable to load suggestions");
	      }
	  }

	  @GetMapping("/assignments/{studentId}")
	  public ResponseEntity<?> viewAssignments(@PathVariable int studentId) {
	      try {
	          return ResponseEntity.ok(studentService.viewStudentAssignments(studentId));
	      } catch (Exception e) {
	          return ResponseEntity.status(500).body("Unable to load assignments");
	      }
	  }
}