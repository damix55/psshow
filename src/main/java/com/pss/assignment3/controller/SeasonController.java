package com.pss.assignment3.controller;

import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.*;
import com.pss.assignment3.service.SeasonService;
import com.pss.assignment3.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("seasons")

public class SeasonController {

    private final SeasonService seasonService;
    private final SeriesService seriesService;

    @Autowired
    public SeasonController(SeasonService seasonService, SeriesService seriesService) {
        this.seasonService = seasonService;
        this.seriesService = seriesService;
    }

    @GetMapping("/{id}")
    public String showSeasonsById(Model model, @PathVariable("id") Long id)
            throws ResourceNotFoundException {
        model.addAttribute("season", seasonService.getSeasonById(id));
        return "seasons-show";
    }

    @GetMapping("/add/{seriesId}")
    public String showAddSeason(Model model, @PathVariable("seriesId") Long seriesId) {
        model.addAttribute("season", new Season());
        model.addAttribute("seriesId", seriesId);
        return ("seasons-add");
    }

    @PostMapping("/add/{seriesId}")
    public String addSeason(Model model, @PathVariable("seriesId") Long seriesId, @Valid @ModelAttribute("season") Season season, BindingResult bindingResult)
            throws ResourceNotFoundException {
        if (bindingResult.hasErrors()) {
            return ("seasons-add");
        } else {
            Season savedSeason = seasonService.addSeason(season);
            seriesService.addSeasons(seriesId, savedSeason);
            return ("redirect:/series/" + seriesId);
        }
    }

    @GetMapping("/update/{seriesId}/{id}")
    public String showUpdateSeason(Model model, @PathVariable("id") Long id, @PathVariable("seriesId") Long seriesId) throws ResourceNotFoundException {
        model.addAttribute("season", seasonService.getSeasonById(id));
        model.addAttribute("seriesId", seriesId);
        return ("seasons-update");
    }

    @PostMapping("/update/{seriesId}/{id}")
    public String updateSeason(Model model, @PathVariable("id") Long id, @PathVariable("seriesId") Long seriesId, @Valid @ModelAttribute("season") Season season, BindingResult bindingResult)
            throws ResourceNotFoundException {
        if (bindingResult.hasErrors()) {
            return ("seasons-update");
        } else {
            seasonService.updateSeason(id, season);
            return ("redirect:/series/" + seriesId);
        }
    }

    @PostMapping("/delete/{seriesId}/{id}")
    public String deleteSeasonsById(@PathVariable("id") Long id, @PathVariable("seriesId") Long seriesId) throws ResourceNotFoundException {
        seasonService.deleteSeasonById(id);
        return ("redirect:/series/" + seriesId);
    }
}