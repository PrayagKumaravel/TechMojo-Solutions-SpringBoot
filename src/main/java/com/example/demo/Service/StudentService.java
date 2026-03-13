package com.example.demo.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.CourseDto;
import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.SectionDto;
import com.example.demo.DTO.StudentDto;
import com.example.demo.Model.CourseModel;
import com.example.demo.Model.SectionModel;
import com.example.demo.Model.StudentModel;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.SectionRepository;
import com.example.demo.Repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;
    public Long createStudent(StudentDto body){
        //dto to entity
        StudentModel studentModel=new StudentModel();
        studentModel.setName(body.getName());
        studentModel.setPresent((long) (body.getPresent()?1:0));
        //sectiopdto tosection model
        
        studentModel.setSectionid(sectionRepository.findById(body.getSection_id().getId())
    .orElseThrow(()-> new RuntimeException("section doesn.t exist")));
        List<CourseDto> courseDtos=body.getCourses() != null? body.getCourses():new ArrayList();
        Set<CourseModel> courseModels=new HashSet<>();
        for(CourseDto data: courseDtos){
            CourseModel courseModel=new CourseModel();
            courseModel=courseRepository.findById(data.getId())
            .orElseThrow(()-> new RuntimeException("Course doesn't exist"));
            courseModels.add(courseModel);
        }
        studentModel.setCourses(courseModels);
        studentModel.setTotaldays((long) (1));

        //saving entity
        StudentModel studentEntity = studentRepository.save(studentModel);//after saving it returns record

        return studentEntity.getId();
    }


    public ResponseDto getById(Long id) {

        StudentModel studentEntity = studentRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Student doesnt exist"));

        StudentDto studentDto=new StudentDto();
            studentDto.setName(studentEntity.getName());
            studentDto.setPresentdays(studentEntity.getPresent());
            studentDto.setTotaldays(studentEntity.getTotaldays());
            SectionModel sectionModel=sectionRepository.findById(studentEntity.getSectionid().getId())
            .orElseThrow(()-> new RuntimeException("Section not found in DB")); 
            studentDto.setSection_id(
                SectionDto.builder().name(sectionModel.getName()).build()
            );
            //list<coursemodel> to list<dto>
            Set<CourseModel> courseModels=studentEntity.getCourses()!=null?studentEntity.getCourses():new HashSet<>();
            List<CourseDto> courseDtos=new ArrayList<>();
            for(CourseModel cModel:courseModels){
                courseDtos.add(
                    CourseDto.builder().name(cModel.getName()).description(cModel.getDescription()).build()
                );
            }
            //peivate List<CourseDto> courses inside studentDto..that wht convert the List<Models> to List<Dto> then set courses
            studentDto.setCourses(courseDtos);
            studentDto.setPercentage((long)((double)studentEntity.getPresent()/(double)studentEntity.getTotaldays())*100);
            ResponseDto response = new ResponseDto();
            List<StudentDto> studenst = new ArrayList<>();
            studenst.add(studentDto);
            response.setStudents(studenst);
            

            return response;

    }

    public ResponseDto getAllStudent(){
        ResponseDto responseDto=new ResponseDto();
        List<StudentModel> studentResponse=studentRepository.findAll();//entity list
        List<StudentDto> studentDtos=new ArrayList<>();
        //entity to dto
        for(StudentModel data:studentResponse){
            StudentDto studentDto=new StudentDto();
            studentDto.setName(data.getName());
            studentDto.setPresentdays(data.getPresent());
            studentDto.setTotaldays(data.getTotaldays());
            SectionModel sectionModel=sectionRepository.findById(data.getSectionid().getId())
            .orElseThrow(()-> new RuntimeException("Section not found in DB")); 
            studentDto.setSection_id(
                SectionDto.builder().name(sectionModel.getName()).build()
            );
            //list<coursemodel> to list<dto>
            Set<CourseModel> courseModels=data.getCourses();
            List<CourseDto> courseDtos=new ArrayList<>();
            for(CourseModel cModel:courseModels){
                courseDtos.add(
                    CourseDto.builder().name(cModel.getName()).description(cModel.getDescription()).build()
                );
            }
            //peivate List<CourseDto> courses inside studentDto..that wht convert the List<Models> to List<Dto> then set courses
            studentDto.setCourses(courseDtos);
            studentDto.setPercentage((long)((double)data.getPresent()/(double)data.getTotaldays())*100);//setting percntage in dto with present and totldays from from entity
            studentDtos.add(studentDto);
        }
        responseDto.setStudents(studentDtos);
        return responseDto;
    }

    public void deleteStudentById(Long id){
        //unlink relationship in join table
        //found teh studentmmodel record...then since everystudents has list of curses, i iterate the courses....
        //nd now that coy=urse hash lis fof students, from that dlete this particular student
        //after all delet the student from student table
        StudentModel studentModel=studentRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("student doesn't exist"));

        if(studentModel.getCourses()!=null){
            for(CourseModel courses:studentModel.getCourses()){
                courses.getStudents().remove(studentModel);
            }
        }
        studentRepository.delete(studentModel);
    }

    public ResponseDto updateSectionId(StudentDto request){
        StudentModel studentModel=studentRepository.findById(request.getId())
        .orElseThrow(()->new RuntimeException("Student doesn't exist"));
        //studentdto hash sectiondto -> studentmodel wants section model
        studentModel.setSectionid(SectionModel.builder().id((request.getSection_id()).getId()).build());
        StudentModel updated=studentRepository.save(studentModel);
        ResponseDto response=new ResponseDto();
        //responsdto hash kist<stduentdto> so convert studentmodel to student dto and store it in list then mapp it rto responseDto
        StudentDto studentDto=new StudentDto();
        studentDto.setId(updated.getId());
        List<StudentDto> studentDtos=new ArrayList<>();
        studentDtos.add(studentDto);
        response.setStudents(studentDtos);
        return response;
    }
}
