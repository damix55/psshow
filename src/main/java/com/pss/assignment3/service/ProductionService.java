package com.pss.assignment3.service;

import com.pss.assignment3.dao.ActorDao;
import com.pss.assignment3.dao.ProductionBaseDao;
import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Actor;
import com.pss.assignment3.model.Production;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public abstract class ProductionService<T extends Production> {

    protected ProductionBaseDao<T> productionDao;
    protected ActorDao actorDao;

    @Autowired
    public ProductionService(ProductionBaseDao<T> productionDao, ActorDao actorDao) {
        this.productionDao = productionDao;
        this.actorDao = actorDao;
    }

    public List<T> getAllProductions() {
        return productionDao.findAll();
    }

    public T getProductionById(long id) throws ResourceNotFoundException {

        return productionDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production ID not found:: " + id));
    }

    public void addProduction(T production) {
        productionDao.save(production);
    }

    public void deleteProductionById(long id) throws ResourceNotFoundException {

        T production = productionDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production ID not found:: " + id));

        productionDao.delete(production);
    }

    public T updateProduction(long id, T newProduction)
            throws ResourceNotFoundException {

        T production = productionDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production ID not found:: " + id));

        production.setTitle(newProduction.getTitle());
        production.setGenre(newProduction.getGenre());
        production.setLanguage(newProduction.getLanguage());
        production.setDescription(newProduction.getDescription());
        production.setPicture(newProduction.getPicture());

        return production;
    }

    public void updateProductionActors(long id, List<Actor> actors)
            throws ResourceNotFoundException {

        T production = productionDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production ID not found:: " + id));

        production.setActors(actors);

        productionDao.save(production);
    }

    public List<Production> searchProductions(String query) {
        List<Production> results = new ArrayList<>();
        query = query.toLowerCase();

        for (Production production : getAllProductions()) {
            String title = production.getTitle().toLowerCase();
            String genre = production.getGenre().toLowerCase();
            String language = production.getLanguage().toLowerCase();

            if (title.contains(query)) {
                results.add(production);
                continue;
            }
            if (genre.toLowerCase().contains(query)) {
                results.add(production);
                continue;
            }
            if (language.contains(query)) {
                results.add(production);
            }
        }

        return results;
    }
}