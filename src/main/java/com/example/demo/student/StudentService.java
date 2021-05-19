package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
	@Autowired	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudentList() {
		return studentRepository.findAll();
	}

	public String addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("email is already been used");
		}
		
		studentRepository.save(student);
		return "successfully added new student details";
	}

	public String deleteStudent(Long studentId) {
		Boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("student with student id"+studentId+"deos not exists");
		}
		studentRepository.deleteById(studentId);
		return "successfully deleted student with student id:"+studentId;
	}

	@Transactional
	public String updateStudent(Long studentId, Student student) {
		Optional<Student> studentOptional = null;
		String name= null;
		String email = null;
		String message=null;
		Boolean flag = false;
		try {
			studentOptional = studentRepository.findById(studentId);
			if(studentOptional.isEmpty()) {
				throw new IllegalStateException("student with student id"+studentId+"deos not exists");
			}

			Student existingStudent = studentOptional.get();
			name = student.getName();
			email = student.getEmail();
			if(name == null || name.length()== 0 || name.equalsIgnoreCase(existingStudent.getName())) {
				flag = true;
				throw new Exception("Name is not present or name is duplicate");
			}
			if(email == null || email.length()== 0 || email.equalsIgnoreCase(existingStudent.getEmail())) {
				flag = true;
				throw new Exception("e-mail is not present or e-mail is duplicate");
			}
			if(!flag) {
				existingStudent.setName(student.getName());
				existingStudent.setEmail(student.getEmail());
			}
			message =  "Successfully updated student details";
		} catch (IllegalStateException e) {
			message =  "Student with student id: "+studentId+" deos not exists";
			throw new IllegalStateException(message);
		} catch (Exception e) {
			message = e.getMessage();
			e.printStackTrace();
		}
		return message;
	}
}
