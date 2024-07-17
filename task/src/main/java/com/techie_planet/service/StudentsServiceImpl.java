package com.techie_planet.service;

import com.techie_planet.data.dtos.StudentDto;
import com.techie_planet.data.dtos.StudentScoreDto;
import com.techie_planet.data.models.Students;
import com.techie_planet.data.models.StudentsReport;
import com.techie_planet.data.models.StudentsScore;
import com.techie_planet.data.models.Subjects;
import com.techie_planet.data.repositories.StudentsRepo;
import com.techie_planet.data.repositories.StudentsScoreRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService{
    private final StudentsRepo studentRepo;
    private final StudentsScoreRepo studentScoreRepo;
    private final ModelMapper modelMapper;
    public StudentsServiceImpl(StudentsRepo studentRepo, StudentsScoreRepo studentScoreRepo, ModelMapper modelMapper) {
        this.studentRepo = studentRepo;
        this.studentScoreRepo = studentScoreRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Students createStudent(StudentDto studentDto) {

        Students studentDto1 = modelMapper.map(studentDto, Students.class);
        Students createStudent = studentRepo.save(studentDto1);
        return modelMapper.map(createStudent, Students.class);
    }



    @Override
    public StudentsScore createScore(StudentScoreDto studentScoreDto) {
        try {
            Students student = studentRepo.findById(studentScoreDto.getStudentId()).orElseThrow(() ->
                    new RuntimeException("student is not in our database"));
            System.out.println(student);

            // Create a new ModelMapper instance
            ModelMapper modelMapper = new ModelMapper();

            // Create a new StudentsScore object
            StudentsScore studentScore = modelMapper.map(studentScoreDto, StudentsScore.class);
            studentScore.setSubjectName(Subjects.valueOf(studentScoreDto.getSubjectName().toUpperCase()));

           StudentsScore newScore = studentScoreRepo.save(studentScore);

            // Use ModelMapper to map properties from studentScoreDto to studentScore

            // Set the student explicitly (ModelMapper might not handle circular references)
            studentScore.setStudent(student);

            // Add the studentScore to the student's subjectScores
            student.getSubjectScores().add(newScore);

            // Save both student and studentScore
            studentRepo.save(student);

            return newScore;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<StudentsReport> generateReport() {

        try{
            List<Students> students = studentRepo.findAll();
            System.out.println(students);
            List<StudentsReport> reports = new ArrayList<>();

            for (Students student : students) {
                StudentsReport report = new StudentsReport(student);
                reports.add(report);
            }

            return reports;

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
