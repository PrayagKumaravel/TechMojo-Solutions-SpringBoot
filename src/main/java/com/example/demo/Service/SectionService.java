package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.ResponseDto;
import com.example.demo.DTO.SectionDto;
import com.example.demo.Exception.InputAlreadyExist;
import com.example.demo.Exception.InputDoesntExist;
import com.example.demo.Model.SectionModel;
import com.example.demo.Repository.SectionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    
    public ResponseDto getAllSections()
    {
        //entity to dto
        List<SectionModel> sectionResponse=sectionRepository.findAll();
        ResponseDto responseDto=new ResponseDto();
        List<SectionDto> sectionDtos=new ArrayList<>();
        for(SectionModel data: sectionResponse){
            SectionDto sectionDto=new SectionDto();
            sectionDto.setId(data.getId());
            sectionDto.setName(data.getName());
            sectionDtos.add(sectionDto);
        }

        responseDto.setSections(sectionDtos);
        return responseDto;
        
    }

    public ResponseDto getSectionById(Long id){
        Optional<SectionModel> sectionModel=sectionRepository.findById(id);
        if(sectionModel.isEmpty()){
            throw new InputDoesntExist("Section with this id doesn't exist");
        }
        //responsedto stores List<SectionDto> , so convert ectionmodle to sectiondto and add it to list
        SectionDto sectionDto=SectionDto.builder().name(sectionModel.get().getName()).build();;
        List<SectionDto> sectionDtos=new ArrayList<>();
        sectionDtos.add(sectionDto);
        ResponseDto response=new ResponseDto();
        response.setSections(sectionDtos);
        return response;
    }
    public ResponseDto createSection(SectionDto data){
        //checking whether than section name exist
        Optional<SectionModel>checker=sectionRepository.findByName(data.getName());
        //since already present retun
        if(checker.isPresent()){
            throw new InputAlreadyExist("Section with this name already exist!! ");
        }
        //dto -> entity
        SectionModel sectionModel=new SectionModel();
        sectionModel.setName(data.getName());
        sectionModel.setUpdatedAt(data.getUpdatedAt());
        SectionModel created_record=sectionRepository.save(sectionModel); 
        return ResponseDto.builder().message("Section "+created_record.getName()+" hash been created Sucessfully").status(200).build();
    }

    public void deleteSectionById(Long id){
        sectionRepository.deleteById(id);
    }

    public ResponseDto updateSectionNameById(SectionDto data){
        Optional<SectionModel> sectionModelchecker=sectionRepository.findById(data.getId());
        if(sectionModelchecker.isEmpty()){
            //return ResponseDto.builder().status(500).message("Section doesn't exist with Id "+data.getId()).build();
            throw new InputDoesntExist("Section with this id doesn't exist!!");
        }
        SectionModel sectionModel=sectionModelchecker.get();
        sectionModel.setName(data.getName());
        SectionModel sectionResponse= sectionRepository.save(sectionModel); 
        //section model to sectiondto
        SectionDto sectionDto=new SectionDto();
        sectionDto.setId(sectionResponse.getId());
        List<SectionDto> sectionDtos=new ArrayList<>();
        sectionDtos.add(sectionDto);
        ResponseDto response=new ResponseDto();
        response.setSections(sectionDtos);
        return response;

    }
    
    
    
    
}
