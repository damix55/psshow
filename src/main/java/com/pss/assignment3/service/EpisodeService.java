package com.pss.assignment3.service;

import com.pss.assignment3.dao.EpisodeDao;
import com.pss.assignment3.dao.SeasonDao;
import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Episode;
import com.pss.assignment3.model.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService {

    private final EpisodeDao episodeDao;
    private final SeasonDao seasonDao;

    @Autowired
    public EpisodeService(EpisodeDao episodeDao, SeasonDao seasonDao) {
        this.episodeDao = episodeDao;
        this.seasonDao = seasonDao;
    }

    public Episode getEpisodeById(Long id) throws ResourceNotFoundException {

        return episodeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Episode ID not found:: " + id));
    }

    public Episode addEpisode(Episode episode, long seasonId)
            throws ResourceNotFoundException {
        Season season = seasonDao.findById(seasonId)
                .orElseThrow(() -> new ResourceNotFoundException("Season ID:: " + seasonId));

        episode.setSeason(season);

        return episodeDao.save(episode);
    }

    public void deleteEpisodeById(Long id) throws ResourceNotFoundException {

        Episode episode = episodeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Episode ID not found:: " + id));

        episodeDao.delete(episode);
    }

    public void updateEpisode(Long id, Episode newEpisode) throws ResourceNotFoundException {

        Episode episode = episodeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Episode ID not found:: " + id));

        episode.setNumber(newEpisode.getNumber());
        episode.setTitle(newEpisode.getTitle());
        episode.setLength(newEpisode.getLength());

        episodeDao.save(episode);
    }
}