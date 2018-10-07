package com.ua.bird.farm.bird_farm.controller;


import com.ua.bird.farm.bird_farm.dto.BirdDto;
import com.ua.bird.farm.bird_farm.dto.BirdListDto;
import com.ua.bird.farm.bird_farm.services.BirdServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/bird")
@Log4j2
public class BirdRestController {
    @Autowired
    private BirdServiceImpl birdService;

    @GetMapping("/show")
    public BirdListDto showAllBird() {
        return birdService.showAll();
    }

    @GetMapping("/getBirdForTypeAndWeight/{type}/{weight}")
    public BirdDto getBirdForTypeAndWeight(@PathVariable("type") String type, @PathVariable("weight") String weight){
        BirdDto birdDto = null;
        try{
            int weightBird = Integer.parseInt(weight);
            birdDto = birdService.getBirdByTypeAndTotalWeight(type, weightBird);
        }catch (Exception e){
            e.printStackTrace();
        }
        return birdDto;
    }
}
