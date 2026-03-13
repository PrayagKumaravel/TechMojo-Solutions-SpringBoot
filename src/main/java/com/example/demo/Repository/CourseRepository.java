package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.CourseModel;
import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<CourseModel,Long>,JpaSpecificationExecutor<CourseModel>{
    Optional<CourseModel> findByName(String name);

    void deleteByName(String name);
}
