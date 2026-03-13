package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.StudentDto;
import com.example.demo.Service.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    
    private final StudentService studentService;

    @PostMapping("/create")
    public  Long createStudent(@RequestBody StudentDto body){
        return studentService.createStudent(body);
    }

    @GetMapping("/get-All")
    public ResponseDto getAllStudent(){
        return studentService.getAllStudent();
    }
    
    @GetMapping("/get-by-id")
    public ResponseDto getById(@RequestParam Long id){
        return studentService.getById(id);
    }
    @DeleteMapping("/delete-By-Id")
    public void deleteById(@RequestParam Long id){
        studentService.deleteStudentById(id);
    }
    @PatchMapping("/update-section-By-Id")
    public ResponseDto updateSectionIdById(@RequestBody StudentDto request){
        return studentService.updateSectionId(request);
    }

    
}
