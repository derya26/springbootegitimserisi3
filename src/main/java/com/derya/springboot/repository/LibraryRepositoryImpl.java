package com.derya.springboot.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.derya.springboot.controller.Library;

public class LibraryRepositoryImpl implements LibraryRespositoryCustom {
	
	@Autowired
	LibraryRepository repository;

	@Override
	public List<Library> findAllByAuthor(String authorName) {
		List<Library> booksWithAuthor = new ArrayList<Library>();
		List<Library> books= repository.findAll();
		
		for(Library item :books) {
			if(item.getAuthor().equalsIgnoreCase(authorName)) {
				booksWithAuthor.add(item);
			}
		}
		return booksWithAuthor;
	}

}
