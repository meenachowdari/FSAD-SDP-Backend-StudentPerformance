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

import com.klef.sdp.entity.StudentAssignment;
import com.klef.sdp.entity.StudentFeedback;
import com.klef.sdp.entity.StudentMarks;
import com.klef.sdp.service.StudentService;
import com.klef.sdp.service.TeacherService;

@RestController
@RequestMapping("teacherapi")
@CrossOrigin(origins = "https://your-frontend.vercel.app")

public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	
	@GetMapping("/")
	public String teacherhome()
	{
	   return "Teacher Controller Demo";
	}
	
//	@PostMapping("/login")
//	  public ResponseEntity<?> verifystudentlogin(@RequestBody Teacher teacher)
//	  {
//		   try
//			{
//				Teacher t = teacherService.verifyTeacherLogin(teacher.getUsername(), teacher.getPassword());
//			
//			    if(t!=null)
//			    {
//			    	return ResponseEntity.status(200).body(t);
//			    }
//			    else
//			    {
//			    	return ResponseEntity.status(401).body("Login Invalid");
//			    }
//			}
//			catch (Exception e) 
//			{
//				return ResponseEntity.status(500).body("Internal Server Error");
//			}
//	  }
	
	@GetMapping("/assignedstudents/{teacherId}")
	public ResponseEntity<?> viewAssignedStudents(@PathVariable int teacherId) {
	    try {
	        return ResponseEntity.ok(teacherService.viewAssignedStudents(teacherId));
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("Unable to load assigned students");
	    }
	}
	
	  @PostMapping("/addmarks")
	  public ResponseEntity<String> addMarks(@RequestBody StudentMarks marks)
	  {
	      return ResponseEntity.ok(teacherService.addMarks(marks));
	  }

	  @PostMapping("/addfeedback")
	  public ResponseEntity<String> addFeedback(@RequestBody StudentFeedback feedback)
	  {
	      return ResponseEntity.ok(teacherService.addFeedback(feedback));
	  }
	    
	  @PostMapping("/addassignment")
	  public ResponseEntity<String> addAssignment(@RequestBody StudentAssignment assignment)
	  {
	      return ResponseEntity.ok(teacherService.addAssignment(assignment));
	  }
	  
	  @GetMapping("/assignments/{teacherId}")
	  public ResponseEntity<?> viewAssignments(@PathVariable int teacherId) {
	      try {
	          return ResponseEntity.ok(teacherService.viewAssignmentsByTeacher(teacherId));
	      } catch (Exception e) {
	          return ResponseEntity.status(500).body("Unable to load assignments");
	      }
	  }
	  
	  @GetMapping("/analytics/totalstudents/{teacherId}")
	  public ResponseEntity<?> getTotalStudents(@PathVariable int teacherId) {
	      try {
	          return ResponseEntity.ok(teacherService.getTotalStudentsCount(teacherId));
	      } catch (Exception e) {
	          return ResponseEntity.status(500).body("Unable to load total students");
	      }
	  }

	  @GetMapping("/analytics/testsgiven/{teacherId}")
	  public ResponseEntity<?> getTestsGiven(@PathVariable int teacherId) {
	      try {
	          return ResponseEntity.ok(teacherService.getTestsGivenCount(teacherId));
	      } catch (Exception e) {
	          return ResponseEntity.status(500).body("Unable to load tests count");
	      }
	  }
	  
	  @GetMapping("/analytics/averagescore/{teacherId}")
	  public ResponseEntity<?> getAverageScore(@PathVariable int teacherId) {
	      try {
	          return ResponseEntity.ok(teacherService.getPassPercentage(teacherId));
	      } catch (Exception e) {
	          return ResponseEntity.status(500).body("Unable to load average score");
	      }
	  }
}

