package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1.0/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/studentlist")
	public List<Student> getStudentList() {
		return studentService.getStudentList();
	}
	
	@PostMapping("/register")
	public String registerNewStudent(@RequestBody Student student) {
		return studentService.addNewStudent(student);
	}
	
	@DeleteMapping("/deletestudent")
	public String deleteStudent(@RequestParam Long studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	@PutMapping("/update")
	public String updateStudent(@RequestParam(required = true) Long studentId, @RequestBody Student student) {
		return studentService.updateStudent(studentId, student);
	}
}
