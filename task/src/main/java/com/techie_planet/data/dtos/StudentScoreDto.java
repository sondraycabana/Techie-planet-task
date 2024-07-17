package com.techie_planet.data.dtos;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class StudentScoreDto {
    String subjectName;
    double score;
    Integer studentId;
}

