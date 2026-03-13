package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Long>,JpaSpecificationExecutor<StudentModel> {
    List<StudentModel> findByName(String name);
}
