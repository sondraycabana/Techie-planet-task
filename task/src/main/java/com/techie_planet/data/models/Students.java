package com.techie_planet.data.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.util.List;
@Entity
@Data
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "student", orphanRemoval = true)
    @JsonManagedReference
    @Lazy
    private List<StudentsScore> subjectScores;

    @Override
    public String toString() {
        // Avoid calling scores.toString()
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", scoresCount=" + (subjectScores != null ? subjectScores.size() : 0) + // Provide count instead of details
                '}';
    }
}
