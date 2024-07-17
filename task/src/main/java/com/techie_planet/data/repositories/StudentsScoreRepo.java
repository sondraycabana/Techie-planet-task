package com.techie_planet.data.repositories;

import com.techie_planet.data.models.StudentsScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsScoreRepo extends JpaRepository<StudentsScore, Integer> {
}
