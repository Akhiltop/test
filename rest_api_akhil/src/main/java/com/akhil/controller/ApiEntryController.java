package com.akhil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.akhil.model.ApiEntries;
import com.akhil.model.ShortEntryDto;
import com.akhil.service.ApiEntryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ApiEntryController {

	@Autowired
	private ApiEntryService apiEntryService;

	
	@GetMapping("/{category}")
	public ResponseEntity<List<ShortEntryDto>> getEntryBycategoryHandler(@PathVariable String category){
		
		List<ShortEntryDto> sed=apiEntryService.getEntryBycategory(category);
		
		//for getting required response while getting executed
		return new ResponseEntity<List<ShortEntryDto>>(sed,HttpStatus.OK);
	};
	
	
	@PostMapping("/entries")
	public ResponseEntity<String> addNewEntryHandler(@RequestBody ApiEntries entry){
		
		String str=apiEntryService.addNewEntry(entry);
		
		//for getting required response while getting executed
		return new ResponseEntity<String>(str,HttpStatus.ACCEPTED);
	}
	
	
}
