package com.example.StudentManagement.service;

import com.example.StudentManagement.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudent();

    public Student savesStudent(Student student);

    Student getStudentById(Long id);

    Student updateStudent(Student student);

    void deleteStudentById(Long id);
}
