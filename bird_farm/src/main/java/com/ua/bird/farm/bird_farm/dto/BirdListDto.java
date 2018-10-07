package com.ua.bird.farm.bird_farm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BirdListDto implements Serializable {

    @JsonProperty("bird_list")
    private List<BirdDto> birdDtos;


}
