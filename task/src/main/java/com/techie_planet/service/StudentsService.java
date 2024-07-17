package com.techie_planet.service;

import com.techie_planet.data.dtos.StudentDto;
import com.techie_planet.data.dtos.StudentScoreDto;
import com.techie_planet.data.models.Students;
import com.techie_planet.data.models.StudentsReport;
import com.techie_planet.data.models.StudentsScore;

import java.util.List;

public interface StudentsService {
    Students createStudent(StudentDto studentDto);
//    StudentsScore createScore(String Subject, double score, int studentId);
StudentsScore createScore(StudentScoreDto studentScoreDto);
    List<StudentsReport> generateReport();

}
