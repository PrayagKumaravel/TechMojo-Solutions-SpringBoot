package com.example.demo.Model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentModel extends BaseModel{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Name is required")
    private String name;
 

    private Long present;

    private Long totaldays;

    //one student belongs to only one section
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="section_id" , referencedColumnName="id")
    private SectionModel sectionid;

    //one studen can opt many course
    @ManyToMany
    @JoinTable(name = "student_course_join" , joinColumns = @JoinColumn(name="student_id"), inverseJoinColumns = @JoinColumn(name="course_id"))
    private Set<CourseModel> courses;
    
}