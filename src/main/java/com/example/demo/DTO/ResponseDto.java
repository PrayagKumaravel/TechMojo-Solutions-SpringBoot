package com.example.demo.DTO;

//response wrapper-> sending consistent json better for frontend
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseDto {
    //http status
    private Integer status;
    private String message;

    //fo paginations
    private Integer totalElements;
    private Integer totalPages;

    //optional ones
    private List<CourseDto> courses;
    private List<StudentDto> students;
    private List<SectionDto> sections;
}
