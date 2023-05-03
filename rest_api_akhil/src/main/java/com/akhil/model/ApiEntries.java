package com.akhil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ApiEntries {
//assuming title is mandatory and unique
	@Id
	private String title;
	private String description;
	private String auth;
	private Boolean https;
	private String cors;
	private String category;
	
}
