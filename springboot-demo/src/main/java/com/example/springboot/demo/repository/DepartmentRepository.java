package com.example.springboot.demo.repository;

import com.example.springboot.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //public Department findByDepartmentName(String departmentName);

    //to get customized query
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    public Department findByDepartmentNameIgnoreCase(String departmentName);
}
