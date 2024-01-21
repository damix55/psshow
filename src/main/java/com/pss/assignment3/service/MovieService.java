package com.pss.assignment3.service;

import com.pss.assignment3.dao.ActorDao;
import com.pss.assignment3.dao.MovieDao;
import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends ProductionService<Movie> {

    @Autowired
    public MovieService(MovieDao productionDao, ActorDao actorDao) {
        super(productionDao, actorDao);
    }

    public Movie updateProduction(long id, Movie newMovie)
            throws ResourceNotFoundException {

        Movie movie = super.updateProduction(id, newMovie);
        movie.setDirector(newMovie.getDirector());
        movie.setYear(newMovie.getYear());
        movie.setLength(newMovie.getLength());

        return productionDao.save(movie);
    }
}