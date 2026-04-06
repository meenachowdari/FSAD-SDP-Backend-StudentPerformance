package com.klef.sdp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="student_table")
public class Student 
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(nullable = false, length = 50)
   private String name;

   @Column(nullable = false, unique = true, length = 50)
   private String email;

   @Column(unique = true, length = 30)
   private String username;

   @Column(nullable = false)
   private String password;

   @Column(nullable = false)
   private LocalDate dateOfBirth;

   @Column(nullable = false, length = 50)
   private String guardianName;

   @Column(nullable = false, length = 15)
   private String guardianPhone;

   
   @Column(nullable = false)
   private String status;

   @CreationTimestamp
   @Column(updatable = false)
   private LocalDateTime registeredAt;

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


   public LocalDate getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public String getGuardianName() {
      return guardianName;
   }

   public void setGuardianName(String guardianName) {
      this.guardianName = guardianName;
   }

   public String getGuardianPhone() {
      return guardianPhone;
   }

   public void setGuardianPhone(String guardianPhone) {
      this.guardianPhone = guardianPhone;
   }

   public LocalDateTime getRegisteredAt() {
      return registeredAt;
   }

   public void setRegisteredAt(LocalDateTime registeredAt) {
      this.registeredAt = registeredAt;
   }

   public LocalDateTime getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(LocalDateTime updatedAt) {
      this.updatedAt = updatedAt;
   }
   
   public String getStatus() {
		return status;
   }

   public void setStatus(String status) {
		this.status = status;
   }

   @Override
public String toString() {
	return "Student [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", password="
			+ password + ", dateOfBirth=" + dateOfBirth + ", guardianName=" + guardianName + ", guardianPhone="
			+ guardianPhone + ", status=" + status + ", registeredAt=" + registeredAt
			+ ", updatedAt=" + updatedAt + "]";
}

  

  
}