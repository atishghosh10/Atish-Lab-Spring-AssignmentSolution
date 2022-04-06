package com.greatlearning.StudentManagement.service;

import java.util.List;

import com.greatlearning.StudentManagement.entity.Student;

import org.springframework.stereotype.Service;

public interface StudentService {

	public List<Student> findAll();

	public Student findById(int theId);

	public void save(Student theStudent);

	public void deleteById(int theId);


}
