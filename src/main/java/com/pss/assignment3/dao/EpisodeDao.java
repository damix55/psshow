package com.pss.assignment3.dao;

import com.pss.assignment3.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeDao extends JpaRepository<Episode, Long> {
}

