package com.ua.store.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BirdDto implements Serializable {

    @JsonProperty("price_per_unit")
    private int pricePerUnit;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("name")
    private String name;

}
