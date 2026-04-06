// com.klef.sdp.dto.StudentSummaryDTO
package com.klef.sdp.dto;

public class StudentDetailsDTO 
{
	
    private int id;
    private String name;
    private String email;
    private String username;
    private String section;
    
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

	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

    
}