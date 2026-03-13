package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.SectionModel;


@Repository
public interface SectionRepository extends JpaRepository<SectionModel,Long>{
    Optional<SectionModel> findByName(String name);
}
