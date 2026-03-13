package com.example.demo.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CourseDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.Service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Course")
public class CourseController {
    
    private final CourseService courseService;
    @PostMapping("/create-course")
    public ResponseDto createCourse(@RequestBody CourseDto request){

        return courseService.createCourse(request);
    }

    @DeleteMapping("/delete-by-name")
    public void deleteByName(@RequestParam String name){
        courseService.deletebyName(name);
    }


    @GetMapping("/get-all-courses")
    public ResponseDto getAllCourses(){
        return courseService.getAllCourses();
    }

    @PatchMapping("/update-course-by-name")
    public ResponseDto updateCourseByName(@RequestBody CourseDto request,@RequestParam String upcoming){
        return courseService.updateCourseByName(request, upcoming);
    }
}
