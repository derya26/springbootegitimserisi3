package com.derya.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.derya.springboot.repository.LibraryRepository;
import com.derya.springboot.service.LibraryService;

@RestController
public class LibraryController {
	
	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;
	
	private static final Logger logger =  LoggerFactory.getLogger(LibraryController.class);
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Library>> getAllBooks(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	
	@PostMapping("/addBook")
	public ResponseEntity addBookImplementation(@RequestBody Library library) {
		String id = library.getIsbn()+library.getAisle();
		AddResponse ad = new AddResponse();
		if(libraryService.checkBookAlreadyExist(id)) {
			logger.info("Book do not exist so creating one");
			
			library.setId(id);
			repository.save(library);
			HttpHeaders headers=new HttpHeaders();
			headers.add("unique", id);
			ad.setMsg("Success book is added");
			ad.setId(library.getId());
			return new ResponseEntity<AddResponse>(ad,headers, HttpStatus.CREATED) ;
		}else {
			ad.setMsg("Book id already exist");
			logger.info("Book already exists so skipping");
		}
		return new ResponseEntity<AddResponse>(ad, HttpStatus.ACCEPTED) ;
	}
	
	@GetMapping("/getBooks/{id}")
	public Library getBooksById(@PathVariable(value="id")String id) {
		try {
			Library lib = repository.findById(id).get();
			return lib;		
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("getBooks/author")
	public List<Library> getBookByAuthorName(@RequestParam(value="authorname")String authorname) {
		return repository.findAllByAuthor(authorname);
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Library> updateBook(@PathVariable(value="id")String id, @RequestBody Library library){
		Library existingBook =repository.findById(id).get();
		existingBook.setAisle(library.getAisle());
		existingBook.setAuthor(library.getAuthor());
		existingBook.setBook_name(library.getBook_name());
		repository.save(existingBook);
		return new ResponseEntity<Library>(existingBook,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBookById(@RequestBody Library library){
		Library libdelete = repository.findById(library.getId()).get();
		repository.delete(libdelete);
		return new ResponseEntity<>("Book is deleted", HttpStatus.CREATED);
		
	}

}
