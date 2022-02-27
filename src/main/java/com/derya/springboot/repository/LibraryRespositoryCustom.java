package com.derya.springboot.repository;

import java.util.List;

import com.derya.springboot.controller.Library;

public interface LibraryRespositoryCustom {
	
	List<Library> findAllByAuthor(String authorName);

}
