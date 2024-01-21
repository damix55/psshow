package com.pss.assignment3.service;

import com.pss.assignment3.dao.ActorDao;
import com.pss.assignment3.dao.SeriesDao;
import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Season;
import com.pss.assignment3.model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesService extends ProductionService<Series> {

    @Autowired
    public SeriesService(SeriesDao productionDao, ActorDao actorDao) {
        super(productionDao, actorDao);
    }

    public Series updateProduction(long id, Series newSeries)
            throws ResourceNotFoundException {

        Series series = super.updateProduction(id, newSeries);
        series.setFinished(newSeries.getFinished());

        return productionDao.save(series);
    }

    public void addSeasons(Long id, Season season)
            throws ResourceNotFoundException {
        Series series = productionDao.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Series ID not found:: " + id));
        series.addSeasons(season);
        productionDao.save(series);
    }
}