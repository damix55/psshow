package com.pss.assignment3.controller;

import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Actor;
import com.pss.assignment3.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public String showActors(Model model) {
        model.addAttribute("actors", actorService.getAllActors());
        return "actors";
    }

    @GetMapping("/{id}")
    public String showActorsById(Model model, @PathVariable("id") Long id) throws ResourceNotFoundException {
        model.addAttribute("actor", actorService.getActorById(id));
        return "actors-show";
    }


    @GetMapping("/search")
    public String showActorsSearch(Model model) {
        return "actors-search";
    }

    @PostMapping("/search")
    public String showActorsSearchResults(Model model, @RequestParam Map<String, String> requestParams) {
        String query = requestParams.get("search").strip().replaceAll("\\s{2,}", " ");
        List<Actor> results = actorService.searchActors(query);

        model.addAttribute("query", query);
        model.addAttribute("actors", results);
        return "actors-search";
    }

    @GetMapping("/add")
    public String showAddActor(Model model) {
        model.addAttribute("actor", new Actor());
        List<Actor> actors = actorService.getAllActors();
        model.addAttribute("actors", actors);
        return ("actors-add");
    }

    @PostMapping("/add")
    public String addActor(Model model, @Valid @ModelAttribute Actor actor, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ("actors-add");
        } else {
            Actor newActor = actorService.addActor(actor);
            return ("redirect:/actors/" + newActor.getId());
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateActor(Model model, @PathVariable("id") Long id) throws ResourceNotFoundException {
        Actor actor = actorService.getActorById(id);
        List<Actor> actors = actorService.getAllActors();
        actors.remove(actor);
        model.addAttribute("actor", actor);
        model.addAttribute("actors", actors);
        return "actors-update";
    }

    @PostMapping("/update/{id}")
    public String updateActor(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute Actor actor, BindingResult bindingResult)
            throws ResourceNotFoundException {
        if (bindingResult.hasErrors()) {
            return ("actors-update");
        } else {
            actorService.updateActor(id, actor);
            return ("redirect:/actors/" + id);
        }
    }

    @PostMapping("delete/{id}")
    public String deleteActorsById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        actorService.deleteActorById(id);
        return ("redirect:/actors");
    }

}