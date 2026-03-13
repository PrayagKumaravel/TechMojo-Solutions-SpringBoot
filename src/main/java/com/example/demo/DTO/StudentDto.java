package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {
    private Long id;

    private String name;

    private Boolean present;

    private Long presentdays;
    private Long totaldays;

    private Long percentage;

    private SectionDto section_id;
    
    private List<CourseDto> courses;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
