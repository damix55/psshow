package com.pss.assignment3.controller;

import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Episode;
import com.pss.assignment3.service.EpisodeService;
import com.pss.assignment3.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("episodes")
public class EpisodeController {

    private final EpisodeService episodeService;
    private final SeasonService seasonService;

    @Autowired
    public EpisodeController(EpisodeService episodeService, SeasonService seasonService) {
        this.episodeService = episodeService;
        this.seasonService = seasonService;
    }

    @GetMapping("/add/{seasonId}")
    public String showAddEpisode(Model model, @PathVariable("seasonId") Long seasonId) {
        model.addAttribute("episode", new Episode());
        model.addAttribute("seasonId", seasonId);
        return ("episodes-add");
    }

    @PostMapping("/add/{seasonId}")
    public String addEpisode(Model model, @PathVariable("seasonId") Long seasonId, @Valid @ModelAttribute("episode") Episode episode, BindingResult bindingResult)
            throws ResourceNotFoundException {
        if (bindingResult.hasErrors()) {
            return ("episodes-add");
        } else {
            Episode savedEpisode = episodeService.addEpisode(episode, seasonId);
            seasonService.addEpisodes(seasonId, savedEpisode);
            long seriesId = seasonService.getSeasonById(seasonId).getSeries().getId();
            return ("redirect:/series/" + seriesId);
        }
    }

    @GetMapping("/update/{seasonId}/{id}")
    public String showUpdateEpisode(Model model, @PathVariable("id") Long id, @PathVariable("seasonId") Long seasonId)
            throws ResourceNotFoundException {
        model.addAttribute("episode", episodeService.getEpisodeById(id));
        model.addAttribute("seasonId", seasonId);
        return ("episodes-update");
    }

    @PostMapping("/update/{seasonId}/{id}")
    public String updateEpisode(Model model, @PathVariable("id") Long id, @PathVariable("seasonId") Long seasonId, @Valid @ModelAttribute("episode") Episode episode, BindingResult bindingResult)
            throws ResourceNotFoundException {
        if (bindingResult.hasErrors()) {
            return ("episodes-update");
        } else {
            episodeService.updateEpisode(id, episode);
            long seriesId = seasonService.getSeasonById(seasonId).getSeries().getId();
            return ("redirect:/series/" + seriesId);
        }
    }

    @PostMapping("/delete/{seasonId}/{id}")
    public String deleteEpisodeById(@PathVariable("id") Long id, @PathVariable("seasonId") Long seasonId) throws ResourceNotFoundException {
        episodeService.deleteEpisodeById(id);
        long seriesId = seasonService.getSeasonById(seasonId).getSeries().getId();
        return ("redirect:/series/" + seriesId);
    }
}
