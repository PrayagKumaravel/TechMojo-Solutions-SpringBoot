package com.example.demo.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//not entity , since this is a field whidch is to be extended by otgher entities
@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class BaseModel{

    private final LocalDateTime createdAt=LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
