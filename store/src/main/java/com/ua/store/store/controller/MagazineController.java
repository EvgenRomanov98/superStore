package com.ua.store.store.controller;

import com.ua.store.store.services.GetBirdFromFarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/magazin")
public class MagazineController {

    @Autowired
    private GetBirdFromFarmServices getBirdFromFarmServices;

    @GetMapping("/birds")
    private String listBirds(Model model) {
        model.addAttribute("birdList", getBirdFromFarmServices.getBirdFromFar());
        return "magaz";
    }
}
