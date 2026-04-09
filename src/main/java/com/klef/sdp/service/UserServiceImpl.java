package com.klef.sdp.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.klef.sdp.entity.Admin;
import com.klef.sdp.entity.Student;
import com.klef.sdp.entity.Teacher;
import com.klef.sdp.repository.AdminRepository;
import com.klef.sdp.repository.StudentRepository;
import com.klef.sdp.repository.TeacherRepository;


@Service
public class UserServiceImpl implements UserService 
{
    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private StudentRepository customerRepo;

    @Autowired
    private TeacherRepository managerRepo;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException 
    {
        // 1. Try Admin - using username
        Optional<Admin> adminOpt = adminRepo.findById(input);   // Admin uses username as ID
        if (adminOpt.isPresent()) 
        {
            Admin admin = adminOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    admin.getUsername(), 
                    admin.getPassword(),
                    List.of(new SimpleGrantedAuthority("ADMIN"))
            );
        }

        // 2. Try Customer - using email
        Optional<Student> customerOpt = customerRepo.findByUsername(input);
        if (customerOpt.isPresent()) 
        {
            Student customer = customerOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    customer.getUsername(), 
                    customer.getPassword(),
                    List.of(new SimpleGrantedAuthority("STUDENT"))
            );
        }

        // 3. Try Service Manager - using manageremail
        Optional<Teacher> managerOpt = managerRepo.findByUsername(input);
        if (managerOpt.isPresent()) 
        {
            Teacher manager = managerOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    manager.getUsername(),
                    manager.getPassword(),
                    List.of(new SimpleGrantedAuthority("TEACHER"))
            );
        }

        throw new UsernameNotFoundException("User not found with input: " + input);
    }

	@Override
	public Object getUserByLogin(String input) 
	{
		 Optional<Admin> adminOpt = adminRepo.findById(input);
		 
	        if (adminOpt.isPresent()) 
	        {
	            return adminOpt.get();
	        }

	        Optional<Student> customerOpt = customerRepo.findByUsername(input);
	        if (customerOpt.isPresent()) 
	        {
	            return customerOpt.get();
	        }

	        Optional<Teacher> managerOpt = managerRepo.findByUsername(input);
	        if (managerOpt.isPresent()) 
	        {
	            return managerOpt.get();
	        }

	        return null;
	    }
}