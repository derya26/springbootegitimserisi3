package com.derya.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.derya.springboot.controller.Library;
import com.derya.springboot.repository.LibraryRepository;

@SpringBootApplication
public class Springbootegitimserisi3Application /*implements CommandLineRunner*/{

	/*
	@Autowired
	LibraryRepository repository;
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(Springbootegitimserisi3Application.class, args);
	}
/*
	@Override
	public void run(String... args) throws Exception {
		Library lib = repository.findById("fdsefr343").get();
		System.out.println(lib.getAuthor());
		Library en = new Library();
		en.setAisle(123);
		en.setAuthor("Rahul");
		en.setBook_name("Devops");
		en.setIsbn("Ikbs");
		en.setId("frghj123");
		repository.save(en);
		List<Library> allRecords =repository.findAll();
		for(Library item:allRecords) {
			System.out.println(item.getBook_name());
		}
		repository.delete(en);
	}
	*/

}
