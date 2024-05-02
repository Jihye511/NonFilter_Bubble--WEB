package com.example.book_service.repository;

import com.example.book_service.entity.GenreEntity;
//import com.example.book_service.model.GenrePreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenrePreferenceRepository extends JpaRepository<GenreEntity, Long> {
}

