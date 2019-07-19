package com.codegym.controller;


import com.codegym.model.City;
import com.codegym.model.Nation;
import com.codegym.service.CityService;
import com.codegym.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/user/cities")
public class UserCityController {
    @Autowired
    private CityService cityService;

    @GetMapping("")
    public ModelAndView listCities(@RequestParam("search") Optional<String> keyword, @PageableDefault(size = 10) Pageable pageable){
        Page<City> cities;
        if (keyword.isPresent()){
            cities=cityService.findAllByNameContaining(keyword.get(),pageable);
        } else {
            cities=cityService.findAll(pageable);
        }
        ModelAndView modelAndView=new ModelAndView("/city/listForUser");
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewCity(@PathVariable("id") Long id){
        City city=cityService.findById(id);
        ModelAndView modelAndView=new ModelAndView("/city/viewForUser");
        modelAndView.addObject("city",city);
        return modelAndView;
    }
}
