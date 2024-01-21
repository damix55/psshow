package com.pss.assignment3.controller;

import com.pss.assignment3.exception.ResourceNotFoundException;
import com.pss.assignment3.model.Production;
import com.pss.assignment3.service.ActorService;
import com.pss.assignment3.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public class ProductionController<T extends Production> {

    protected String type;

    protected ProductionService<T> productionService;
    protected ActorService actorService;

    @Autowired
    public ProductionController(ProductionService<T> productionService, ActorService actorService) {
        this.productionService = productionService;
        this.actorService = actorService;
    }

    @GetMapping
    public String showProductions(Model model) {
        model.addAttribute(type, productionService.getAllProductions());
        return type;
    }

    @GetMapping("/{id}")
    public String showProductionById(Model model, @PathVariable("id") Long id) throws ResourceNotFoundException {
        model.addAttribute(type, productionService.getProductionById(id));
        return type + "-show";
    }

    @GetMapping("/add")
    public String showAddProduction(Model model) {
        return (type + "-add");
    }

    @GetMapping("/update/{id}")
    public String showUpdateProduction(Model model, @PathVariable("id") Long id) throws ResourceNotFoundException {
        model.addAttribute(type, productionService.getProductionById(id));
        return type + "-update";
    }

    @PostMapping("delete/{id}")
    public String deleteProductionById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        productionService.deleteProductionById(id);
        return ("redirect:/" + type);
    }

    @GetMapping("/{id}/actors")
    public String showProductionActors(Model model, @PathVariable("id") Long id) throws ResourceNotFoundException {
        model.addAttribute("production", productionService.getProductionById(id));
        model.addAttribute("actors", actorService.getAllActors());
        return "production-actors";
    }

    @PostMapping("/{id}/actors")
    public String updateProductionActors(Model model, @PathVariable("id") Long id, @ModelAttribute T production) throws ResourceNotFoundException {
        productionService.updateProductionActors(id, production.getActors());
        return ("redirect:/" + type + "/" + id);
    }

    @GetMapping("/search")
    public String showProductionsSearch(Model model) {
        return type + "-search";
    }

    @PostMapping("/search")
    public String showProductionsSearchResults(Model model, @RequestParam Map<String, String> requestParams) {
        String query = requestParams.get("search").strip().replaceAll("\\s{2,}", " ");
        List<Production> results = productionService.searchProductions(query);

        model.addAttribute("query", query);
        model.addAttribute(type, results);
        return type + "-search";
    }
}
