package com.example.StudentManagement;

import com.example.StudentManagement.entity.Student;
import com.example.StudentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementApplication implements CommandLineRunner {

	//https://www.youtube.com/watch?v=Ku3gsv7_bCc&t=0s
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		/*Student student1 = new Student("sandeep", "suvarna", "sandeep@gmail.com");
		studentRepository.save(student1);

		Student student2 = new Student("niriksha", "suvarna", "niriksha@gmail.com");
		studentRepository.save(student2);

		Student student3 = new Student("anup", "nairy", "anu@gmail.com");
		studentRepository.save(student3);*/
	}
}
