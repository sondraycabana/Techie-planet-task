package com.techie_planet.controller;

import com.techie_planet.data.dtos.StudentDto;
import com.techie_planet.data.dtos.StudentScoreDto;
import com.techie_planet.data.models.StudentsReport;
import com.techie_planet.service.StudentsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j

@RestController("/student")
public class StudentResultController {
    @Autowired
    private StudentsService studentsService;

    Logger logger;

    public StudentResultController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.ok(studentsService.createStudent(studentDto));
    }


    @PostMapping("/result")
    public ResponseEntity<?> createScore(@RequestBody StudentScoreDto studentScoreDto){
        try {
            System.out.println(studentScoreDto);

            return ResponseEntity.ok(studentsService.createScore( studentScoreDto));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/reports")
    public ResponseEntity<List<StudentsReport>> getStudentReports() {
        try {
            List<StudentsReport> reports = studentsService.generateReport();
            return ResponseEntity.ok(reports);

        } catch (Exception e) {
            // Log the exception for further investigation
            logger.error("Error generating student reports", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
