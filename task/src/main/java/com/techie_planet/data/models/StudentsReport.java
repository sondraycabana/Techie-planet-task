package com.techie_planet.data.models;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class StudentsReport {

    private Students student;

    private Map<String, Double> subjectScores;

    private Double meanScore;

    private Double medianScore;

    private Double modeScore;

    public StudentsReport(Students student) {
        this.student = student;
        this.subjectScores = new HashMap<>();
        calculateScores();
    }

    private void calculateScores() {
        List<StudentsScore> scores = student.getSubjectScores();

        // Calculate subject scores
        for (StudentsScore score : scores) {
            subjectScores.put(score.getSubjectName().toString(), score.getScore());
        }

        // Calculate mean score
        meanScore = scores.stream().mapToDouble(StudentsScore::getScore).average().orElse(0.0);

        // Calculate median score
        List<Double> sortedScores = scores.stream().map(StudentsScore::getScore).sorted().toList();
        int midIndex = sortedScores.size() / 2;
        medianScore = sortedScores.get(midIndex);
        if (sortedScores.size() % 2 == 0) {
            int prevMid = midIndex - 1;
            medianScore = (medianScore + sortedScores.get(prevMid)) / 2;
        }

        // Calculate mode score (simple implementation, can be optimized for efficiency)
        Map<Double, Integer> scoreCount = new HashMap<>();
        for (Double score : scores.stream().map(StudentsScore::getScore).toList()) {
            scoreCount.put(score, scoreCount.getOrDefault(score, 0) + 1);
        }

        int maxCount = 0;
        modeScore = null;
        for (Map.Entry<Double, Integer> entry : scoreCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                modeScore = entry.getKey();
            }
        }
    }

}
