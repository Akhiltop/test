package com.akhil.service;

import java.util.List;

import com.akhil.exception.CategoryNotFoundException;
import com.akhil.exception.InvalidEntryException;
import com.akhil.model.ApiEntries;
import com.akhil.model.ShortEntryDto;

public interface ApiEntryService {

	public List<ShortEntryDto> getEntryBycategory(String category) throws CategoryNotFoundException;
	
	public String addNewEntry(ApiEntries entry) throws InvalidEntryException;
	
}
