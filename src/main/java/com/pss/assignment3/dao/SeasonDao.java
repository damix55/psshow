package com.pss.assignment3.dao;

import com.pss.assignment3.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonDao extends JpaRepository<Season, Long> {

}

