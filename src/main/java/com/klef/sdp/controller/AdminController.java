package com.klef.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.sdp.entity.Admin;
import com.klef.sdp.entity.Student;
import com.klef.sdp.entity.Teacher;
import com.klef.sdp.service.AdminService;


@RestController
@RequestMapping("adminapi")
@CrossOrigin(originPatterns = {"http://localhost:2023", "https://*.vercel.app"})
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/")
	public String home()
	{
		return "Full Stack SDP Project";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> checkadminlogin(@RequestBody Admin admin)
	{
		try
		{
			Admin a = adminService.verifyAdminLogin(admin.getUsername(), admin.getPassword());
		
		    if(a!=null)
		    {
		    	return ResponseEntity.status(200).body(admin);
		    }
		    else
		    {
		    	return ResponseEntity.status(401).body("Login Invalid");
		    }
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
	
	@PostMapping("/addteacher")
	public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher)
	{
		try 
		{
			String output = adminService.addTeacher(teacher);
			return ResponseEntity.status(201).body(output);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
	
	@GetMapping("/viewallteachers")
	public ResponseEntity<?> viewAllTeachers(){
		
		try
	    {
	        List<Teacher> teachers = adminService.viewAllTeachers();
	        
	        if(teachers.size()>0)
	        {
	            return ResponseEntity.status(200).body(teachers);   	
	        }
	        else
	        {
	        	return ResponseEntity.status(204).body("No Data Found");
	        }
	        
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Error Fetching Teachers");
	    }
	}
	
	@PutMapping("/verifystudent/{id}/{status}")
    public ResponseEntity<String> verifyStudent(@PathVariable int id, @PathVariable String status)
    {
        String output = adminService.verifyStudentRegistration(id, status);
        return ResponseEntity.ok(output);
    }
	
	@GetMapping("/viewallstudents")
	public ResponseEntity<?> viewAllStudents()
	{
		try
	    {
	        List<Student> students = adminService.viewAllStudents();
	        if(students.size()>0)
	        return ResponseEntity.ok(students);
	        else
	        return ResponseEntity.noContent().build(); //204 - data not found
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Error Fetching Customers");
	    }
	}
	
	@DeleteMapping("/deleteteacher")
	public ResponseEntity<String> deleteteacher(@RequestParam int id)
	{
	    try
	    {
	        String output = adminService.deleteTeacher(id);
            return ResponseEntity.status(200).body(output);
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Internal Server Error");
	    }
	}
	
	@DeleteMapping("/deletestudent")
	public ResponseEntity<String> deleteStudent(@RequestParam int id)
	{
	    try
	    {
	        String output = adminService.deleteStudent(id);
            return ResponseEntity.status(200).body(output);
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Internal Server Error");
	    }
	}
	
	
}