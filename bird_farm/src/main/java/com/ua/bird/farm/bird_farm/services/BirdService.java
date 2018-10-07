package com.ua.bird.farm.bird_farm.services;


import com.ua.bird.farm.bird_farm.dto.BirdDto;

public interface BirdService {

   BirdDto getBirdByTypeAndTotalWeight(String type, int weight);

}
