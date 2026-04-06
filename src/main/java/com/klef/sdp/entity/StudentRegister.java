package com.klef.sdp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "studentregister_table")
public class StudentRegister
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int studentId;
	
	@Column(nullable = false)
	private int teacherId;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime mappedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public LocalDateTime getMappedAt() {
		return mappedAt;
	}

	public void setMappedAt(LocalDateTime mappedAt) {
		this.mappedAt = mappedAt;
	}

	@Override
	public String toString() {
		return "StudentRegister [id=" + id + ", studentId=" + studentId + ", teacherId=" + teacherId + ", mappedAt="
				+ mappedAt + "]";
	}
	
}