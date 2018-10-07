package com.ua.bird.farm.bird_farm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ua.bird.farm.bird_farm.dto.BirdDto;
import com.ua.bird.farm.bird_farm.services.BirdServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
@Slf4j
public class BirdController {

    @Autowired
    private BirdServiceImpl birdService;

    @GetMapping("total")
    public String getCountTypeBird(Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList<BirdDto> birds = new ArrayList<>(birdService.getAllBirdWithSale());
        model.addAttribute("listBird", birds);
        model.addAttribute("jsonBird", objectMapper.writeValueAsString(birds));

        return "index";
    }

    @PostMapping("addBird")
    public String addBird(@RequestParam("typeBird") String type, @RequestParam("totalWeight") String totalWeight,
                          @RequestParam("pricePerKg") String pricePerKg) {
        try {
            BirdDto birdDto = new BirdDto(type,Integer.parseInt(totalWeight),Integer.parseInt(pricePerKg));
            birdService.addBird(birdDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/total";
    }
}
