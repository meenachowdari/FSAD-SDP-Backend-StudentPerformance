package com.klef.sdp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.sdp.entity.StudentRegister;

public interface StudentRegisterRepository extends JpaRepository<StudentRegister, Integer>
{
	boolean existsByStudentIdAndTeacherId(int studentId, int teacherId);
	List<StudentRegister> findByStudentId(int studentId);
	Optional<StudentRegister> findTopByStudentIdOrderByMappedAtDesc(int studentId);
	List<StudentRegister> findByTeacherId(int teacherId);
	
	long countByTeacherId(int teacherId);
}