package com.pss.assignment3.controller;

import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Series;
import com.pss.assignment3.service.ActorService;
import com.pss.assignment3.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("series")
public class SeriesController extends ProductionController<Series> {

    private SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService, ActorService actorService) {
        super(seriesService, actorService);
        this.type = "series";
    }

    public String showAddProduction(Model model) {
        model.addAttribute(type, new Series());
        return super.showAddProduction(model);
    }

    @PostMapping("/add")
    public String addProduction(Model model, @Valid @ModelAttribute("series") Series series, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ("series-add");
        } else {
            productionService.addProduction(series);
            return ("redirect:/series/" + series.getId());
        }
    }

    @PostMapping("/update/{id}")
    public String updateProduction(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("series") Series series, BindingResult bindingResult)
            throws ResourceNotFoundException {
        if (bindingResult.hasErrors()) {
            return ("series-update");
        } else {
            productionService.updateProduction(id, series);
            return ("redirect:/series/" + id);
        }
    }


}
