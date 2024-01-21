package com.pss.assignment3.controller;

import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Movie;
import com.pss.assignment3.service.ActorService;
import com.pss.assignment3.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("movies")
public class MovieController extends ProductionController<Movie> {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService, ActorService actorService) {
        super(movieService, actorService);
        this.type = "movies";
    }

    public String showAddProduction(Model model) {
        model.addAttribute(type, new Movie());
        return super.showAddProduction(model);
    }

    @PostMapping("/add")
    public String addProduction(Model model, @Valid @ModelAttribute("movies") Movie movie, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ("movies-add");
        } else {
            productionService.addProduction(movie);
            return ("redirect:/movies/" + movie.getId());
        }
    }

    @PostMapping("/update/{id}")
    public String updateProduction(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("movies") Movie movie, BindingResult bindingResult)
            throws ResourceNotFoundException {
        if (bindingResult.hasErrors()) {
            return ("movies-update");
        } else {
            productionService.updateProduction(id, movie);
            return ("redirect:/movies/" + id);
        }
    }


}
