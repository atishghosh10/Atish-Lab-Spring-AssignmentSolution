package com.greatlearning.StudentManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.StudentManagement.entity.Student;
import com.greatlearning.StudentManagement.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
		
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {

		
		List<Student> students = studentService.findAll();

		
		theModel.addAttribute("Students", students);

		return "list-Students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Student theStudent = new Student();

		theModel.addAttribute("Student", theStudent);

		return "Student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model theModel) {

		// get the Student from the service
		Student theStudent = studentService.findById(id);

		// set Student as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);

		// send over to our form
		return "Student-form";
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department );
			theStudent.setCountry(country);
		} else
			theStudent = new Student(id, name, department, country);
		
		
		studentService.save(theStudent);

		
		return "redirect:/student/list";

	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int id) {

		// delete the Student
		studentService.deleteById(id);

		// redirect to /Students/list
		return "redirect:/student/list";

	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}
