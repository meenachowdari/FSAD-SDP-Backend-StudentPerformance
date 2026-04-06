package com.klef.sdp.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="teacher_table")
public class Teacher 
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(nullable = false, length = 50)
   private String name;

   @Column(nullable = false, unique = true, length = 50)
   private String email;

   @Column(nullable = false, unique = true, length = 30)
   private String username;

   @Column(nullable = false)
   private String password;

   @Column(nullable = false, length = 15)
   private String phone;

   @Column(nullable = false, length = 50)
   private String department;

   @Column(name = "subject")
   private List<String> subjects;

   @Column(length = 10)
   private String assignedSection;

   @Column(nullable = false)
   private Boolean isActive;

   @CreationTimestamp
   @Column(updatable = false)
   private LocalDateTime createdAt;

   @UpdateTimestamp
   private LocalDateTime updatedAt;

   public int getId() {
	return id;
   }

   public void setId(int id) {
	this.id = id;
   }

   public String getName() {
	return name;
   }

   public void setName(String name) {
	this.name = name;
   }

   public String getEmail() {
	return email;
   }

   public void setEmail(String email) {
	this.email = email;
   }

   public String getUsername() {
	return username;
   }

   public void setUsername(String username) {
	this.username = username;
   }

   public String getPassword() {
	return password;
   }

   public void setPassword(String password) {
	this.password = password;
   }

   public String getPhone() {
	return phone;
   }

   public void setPhone(String phone) {
	this.phone = phone;
   }

   public String getDepartment() {
	return department;
   }

   public void setDepartment(String department) {
	this.department = department;
   }

   public List<String> getSubjects() {
	return subjects;
   }

   public void setSubjects(List<String> subjects) {
	this.subjects = subjects;
   }

   public String getAssignedSection() {
	return assignedSection;
   }

   public void setAssignedSection(String assignedSection) {
	this.assignedSection = assignedSection;
   }

   public Boolean getIsActive() {
	return isActive;
   }

   public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
   }

   public LocalDateTime getCreatedAt() {
	return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
   }

   public LocalDateTime getUpdatedAt() {
	return updatedAt;
   }

   public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
   }

   @Override
   public String toString() {
	return "Teacher [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", password="
			+ password + ", phone=" + phone + ", department=" + department + ", subjects=" + subjects
			+ ", assignedSection=" + assignedSection + ", isActive=" + isActive + ", createdAt=" + createdAt
			+ ", updatedAt=" + updatedAt + "]";
   }


}