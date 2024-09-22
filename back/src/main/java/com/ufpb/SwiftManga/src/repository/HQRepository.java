package com.ufpb.SwiftManga.src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpb.SwiftManga.src.model.HQ;

import java.util.List;

@Repository
public interface HQRepository extends JpaRepository<HQ, Long> {
    // List<HQ> findByUserId(Long userId);
}