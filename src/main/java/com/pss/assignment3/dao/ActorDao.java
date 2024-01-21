package com.pss.assignment3.dao;

import com.pss.assignment3.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorDao extends JpaRepository<Actor, Long> {

}

