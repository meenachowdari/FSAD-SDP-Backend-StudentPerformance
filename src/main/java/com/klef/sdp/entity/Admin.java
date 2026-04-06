package com.klef.sdp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="admin_table")
public class Admin 
{
   @Id
   @Column(nullable = false, unique = true, length = 30)
   private String username;

   @Column(nullable = false)
   private String password;


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

   @Override
   public String toString() {
	return "Admin [username=" + username + ", password=" + password + "]";
   }
  
}