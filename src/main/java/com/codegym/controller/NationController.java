package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Nation;
import com.codegym.service.CityService;
import com.codegym.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/nations")
public class NationController {

    @Autowired
    private NationService nationService;

    @GetMapping
    public ModelAndView list(){
        Iterable<Nation> nations=nationService.findAll();
        ModelAndView modelAndView=new ModelAndView("/nation/list");
        modelAndView.addObject("nations",nations);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView=new ModelAndView("/nation/create");
        modelAndView.addObject("nation",new Nation());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("nation") Nation nation){
        nationService.save(nation);
        ModelAndView modelAndView=new ModelAndView("/nation/create");
        modelAndView.addObject("nation",new Nation());
        modelAndView.addObject("message","New nation is created successfully");
        return modelAndView;
    }

}
