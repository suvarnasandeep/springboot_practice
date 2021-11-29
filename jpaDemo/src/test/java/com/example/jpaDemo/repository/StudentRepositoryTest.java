package com.example.jpaDemo.repository;

import com.example.jpaDemo.entity.Guardian;
import com.example.jpaDemo.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
    }

   /* @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("sandeep1@gmail.com")
                .firstName("sandeep")
                .lastName("suvarna")
                .guardianName("Niriksha")
                .guardianEmail("niri@gmail.com")
                .guardianMobile("123456")
                .build();

        studentRepository.save(student);
    }*/

   @Test
   public void saveStudentWithGuardian(){

       Guardian guardian = Guardian.builder()
               .name("Manjunath")
               .mobile("78787878")
               .email("nairy@gmail.com")
               .build();
       Student student = Student.builder()
               .firstName("Anup")
               .emailId("anup@gmail.com")
               .lastName("kumar")
               .guardian(guardian)
               .build();

       studentRepository.save(student);
   }

    @Test
    public void  printAllStudents(){
        List<Student> studentList = studentRepository.findAll();

        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("sandeep");
        System.out.println(students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("an");
        System.out.println(students);
    }

    @Test
    public void printStudentByGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("niriksha");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByLastnNameNotNull(){
        List<Student> students = studentRepository.findByLastNameNotNull();
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByEmailAddress(){
       Student student = studentRepository.getStudentByEmailAddress(
               "sandeep@gmail.com"
       );
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String student = studentRepository.getStudentFirstNameByEmailAddress(
                "sandeep@gmail.com"
        );
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstNameByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative(
                "sandeep@gmail.com"
        );
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstNameByEmailAddressNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNamedParam(
                "sandeep@gmail.com"
        );
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentFirstNameByEmailAddressNamedParam(){
        int student = studentRepository.updateStudentNameByEmailId(
                "rakshi",
                "sandeep@gmail.com"
        );
        System.out.println("student = " + student);
    }
}