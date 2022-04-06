package com.greatlearning.StudentManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.StudentManagement.entity.Student;
import com.greatlearning.StudentManagement.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

//	@Override
//	public Student findById(int theId) {
//		List<Student> students = studentRepository.findAll();
//		Student temp = studentRepository.findById(theId).get();
//		return temp;
//	}

	@Override
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(theId);
	}

	@Override
	public Student findById(int theId) {
		// TODO Auto-generated method stub
		List<Student> students = studentRepository.findAll();
		Student temp = studentRepository.findById(theId).get();
		return temp;
	}

}
