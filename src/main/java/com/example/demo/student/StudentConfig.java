package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student john = new Student("John", "john@mail.com", LocalDate.of(1990, Month.JANUARY, 01));
			Student jane = new Student("Jane", "jane@mail.com", LocalDate.of(1986, Month.DECEMBER, 31));
			studentRepository.saveAll(List.of(john, jane));
		};
		
	}
}
