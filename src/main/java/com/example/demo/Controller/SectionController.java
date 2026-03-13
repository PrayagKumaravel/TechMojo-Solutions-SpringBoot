package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.SectionDto;
import com.example.demo.Service.SectionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/section")
@RequiredArgsConstructor
public class SectionController {


 private final SectionService sectionService;

    @GetMapping("/get-all-sections")
    public ResponseDto getAllSections()
    {
        return sectionService.getAllSections(); 
    }

    @PostMapping("/Create-section")
    public ResponseDto createSection(@RequestBody SectionDto section){
        return sectionService.createSection(section);
    }
    
    @DeleteMapping("/delete-by-id")
    public void deleteById(@RequestParam Long id){
        sectionService.deleteSectionById(id);
    }
    
    @PatchMapping("/update-section-by-id")
    public ResponseDto updatesectionId(@RequestBody SectionDto data){
        return sectionService.updateSectionNameById(data);
    }

    @GetMapping("/get-by-id")
    public ResponseDto getById(@RequestParam Long id){
        return sectionService.getSectionById(id);
    }
}
