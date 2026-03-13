package com.example.demo.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CourseDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.Model.CourseModel;
import com.example.demo.Model.StudentModel;
import com.example.demo.Repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
    
    private final CourseRepository courseRepository;
    public ResponseDto createCourse(CourseDto request){
        Optional<CourseModel> checker=courseRepository.findByName(request.getName());
        if(checker.isPresent()){
            return ResponseDto.builder().status(500).message("Course with name "+request.getName()+" already exist!!").build();
        }
        CourseModel courseModel=CourseModel.builder().name(request.getName()).description(request.getDescription()).build();
        courseRepository.save(courseModel);//saved to db
        return ResponseDto.builder().status(200).message("Course has been created sucessfully").build();
    }

    public void deletebyName(String name){
        CourseModel course = courseRepository.findByName(name)
        .orElseThrow(() -> new RuntimeException("Course with name '" + name + "' not found"));
        if (course.getStudents() != null) {
            for (StudentModel student : course.getStudents()) {
            student.getCourses().remove(course);
            }
        }

        courseRepository.delete(course);
    }
    
    public ResponseDto getAllCourses(){
        List<CourseModel> courseModel=courseRepository.findAll();
        //entity to dto List<CourseDto>
        List<CourseDto> courseDtos=new ArrayList<>();
        for(CourseModel data: courseModel){
            courseDtos.add(CourseDto.builder().name(data.getName())
            .description(data.getDescription())
            .createdAt(data.getCreatedAt()).updatedAt(data.getUpdatedAt())
            .build());
        }

        return ResponseDto.builder().status(200).courses(courseDtos).build();
    }
    public ResponseDto updateCourseByName(CourseDto request,String upcoming){
        Optional<CourseModel> course=courseRepository.findByName(request.getName());
        if(course.isEmpty()){
            return ResponseDto.builder().status(500).message("No course with name "+request.getName()+" exist!!").build();
        }
        //updating
        CourseModel courseModel=course.get(); //.get() used to get the content indside optinol
        courseModel.setName(upcoming);
        courseRepository.save(courseModel);
        return ResponseDto.builder().status(200).message("updation sucessfull").build();
    }


}
