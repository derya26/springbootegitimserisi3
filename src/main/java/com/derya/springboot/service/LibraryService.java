package com.derya.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.derya.springboot.controller.Library;
import com.derya.springboot.repository.LibraryRepository;

@Service
public class LibraryService {
	
	@Autowired
	LibraryRepository repository;
	
	public String buildId(String isbn, int aisle) {
		return isbn+aisle;
	}
	
	public boolean checkBookAlreadyExist(String id) {
		Optional<Library> lib=repository.findById(id);
		if(lib.isPresent()) {
			return true;
		}
		else {
			return false;
		}
	}

}
