package com.derya.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.derya.springboot.controller.Library;


public interface LibraryRepository extends JpaRepository<Library, String>,LibraryRespositoryCustom{

}
