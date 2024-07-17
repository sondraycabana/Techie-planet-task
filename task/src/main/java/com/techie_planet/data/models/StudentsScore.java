package com.techie_planet.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentsScore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Subjects subjectName;
    private Double score;

    @ManyToOne
//    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Students student;

    @Override
    public String toString() {
        // Avoid calling student.toString()
        return "StudentScore{" +
                "id=" + id +
                ", score=" + score +
                ", studentId=" + (student != null ? student.getId() : null) +
                '}';
    }
}
