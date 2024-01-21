package com.pss.assignment3.service;

import com.pss.assignment3.dao.SeasonDao;
import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Episode;
import com.pss.assignment3.model.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SeasonService {

    private final SeasonDao seasonDao;

    @Autowired
    public SeasonService(SeasonDao seasonDao) {
        this.seasonDao = seasonDao;
    }

    public Season getSeasonById(Long id)
            throws ResourceNotFoundException {

        return seasonDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Season ID not found:: " + id));
    }

    public Season addSeason(Season season) {
        return seasonDao.save(season);
    }

    public void deleteSeasonById(Long id)
            throws ResourceNotFoundException {

        Season season = seasonDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Season ID not found:: " + id));

        seasonDao.delete(season);
    }

    public void updateSeason(Long id, Season newSeason) throws ResourceNotFoundException {

        Season season = seasonDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Season ID not found:: " + id));

        season.setNumber(newSeason.getNumber());
        season.setYear(newSeason.getYear());

        seasonDao.save(season);
    }

    public void addEpisodes(Long id, Episode episode)
            throws ResourceNotFoundException {
        Season season = seasonDao.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Season ID not found:: " + id));
        season.addEpisodes(episode);
        this.seasonDao.save(season);
    }
}