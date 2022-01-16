package com.excel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.backend.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, String>{

}
