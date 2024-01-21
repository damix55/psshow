package com.pss.assignment3.dao;

import com.pss.assignment3.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductionBaseDao<T extends Production> extends JpaRepository<T, Long> {

}
