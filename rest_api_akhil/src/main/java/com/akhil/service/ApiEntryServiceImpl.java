package com.akhil.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.akhil.exception.CategoryNotFoundException;
import com.akhil.exception.InvalidEntryException;
import com.akhil.model.ApiEntries;
import com.akhil.model.ApiFormatEntries;
import com.akhil.model.ShortEntryDto;
import com.akhil.repository.ApiEntriesDao;

@Service
public class ApiEntryServiceImpl implements ApiEntryService{

	@Autowired
	private ApiEntriesDao apiEntriesDao;
	
	
	@Override
	public List<ShortEntryDto> getEntryBycategory(String category) throws CategoryNotFoundException {
		// TODO Auto-generated method stub
		
		//base url
		String url="https://api.publicapis.org/entries";
		
		RestTemplate rt=new RestTemplate();
		
		
		//for getting data from the url
		ApiFormatEntries afe=rt.getForObject(url, ApiFormatEntries.class);
		
		
		//for getting only entries from provided url
		List<ApiEntries> aentry=afe.getEntries();
		
		//to list required title and description
		List<ShortEntryDto> sentry=new ArrayList<>();
		
		for(ApiEntries ae:aentry) {
			
			//checking if the first word equals the category name and adding to the list
			if(ae.getCategory().split(" &")[0].equals(category)) {
				
				sentry.add(new ShortEntryDto(ae.getTitle(),ae.getDescription()));
				
			}
			
			
		}
		
		if(sentry.size()==0) {
			
			//if its empty either category name may be wrong or improper
			throw new CategoryNotFoundException("No category found with the name "+category);
			
		}
		
		
		return sentry;
	}

	@Override
	public String addNewEntry(ApiEntries entry) throws InvalidEntryException {
		// TODO Auto-generated method stub
		String st="Entry not added successfully";
		
		try {
				
			//if this fails format is unproper and catches the error
			apiEntriesDao.save(entry);
			
			String url="https://api.publicapis.org/entries";
			
			RestTemplate rt=new RestTemplate();
			
			//posting the data to the url
			rt.postForObject(url,entry,ApiEntries.class);
	
			st="Entry added successfully with the title "+entry.getTitle();
			
		}catch(Exception e) {
			
			st="Invalid entry details are provided";
			
			//incase of any exception catched here
			throw new InvalidEntryException(st);
			
		}
		
		
		return st;
	}

}
