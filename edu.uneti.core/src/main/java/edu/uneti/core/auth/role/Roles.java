package edu.uneti.core.auth.role;

import lombok.Getter;

@Getter
public enum Roles {
	
	ADMIN("ROLE_ADMIN", "ADMIN", "roles.admin", 2),
	TEACHER("ROLE_TEACHER", "TEACHER", "roles.teacher", 1),
	STUDENT("ROLE_STUDENT", "STUDENT", "roles.student", 0);
	
	private String value;
	
	private String description;
	
	private String label;
	
	private Integer type;
	
	private Roles(String value, String description, String label, Integer type) {
		this.value = value;
		this.description = description;
		this.label = label;
		this.type = type;
	}
	
	

}
