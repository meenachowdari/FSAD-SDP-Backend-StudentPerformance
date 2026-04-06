package com.klef.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.sdp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	 Student findByUsernameAndPassword(String username,String password);
}
