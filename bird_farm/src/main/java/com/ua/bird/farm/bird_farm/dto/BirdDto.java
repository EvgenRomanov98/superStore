package com.ua.bird.farm.bird_farm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BirdDto implements Serializable {

    @JsonProperty
    private String typeBird;
    @JsonProperty
    private int totalWeight;
    @JsonProperty
    private int pricePerKg;
    @JsonProperty
    private int pricePerKgFrom10to30kg;
    @JsonProperty
    private int pricePerKgFrom30kg;

    public BirdDto() {
    }


    public BirdDto(String typeBird, int totalWeight, int pricePerKg, int pricePerKgFrom10to30kg, int pricePerKgFrom30kg) {
        this.typeBird = typeBird;
        this.totalWeight = totalWeight;
        this.pricePerKg = pricePerKg;
        this.pricePerKgFrom10to30kg = pricePerKgFrom10to30kg;
        this.pricePerKgFrom30kg = pricePerKgFrom30kg;
    }

    public BirdDto(String typeBird, int totalWeight, int pricePerKg) {
        this.typeBird = typeBird;
        this.totalWeight = totalWeight;
        this.pricePerKg = pricePerKg;
    }


}
