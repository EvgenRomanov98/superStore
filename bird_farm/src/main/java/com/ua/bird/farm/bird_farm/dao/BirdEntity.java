package com.ua.bird.farm.bird_farm.dao;

import lombok.Data;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Data
@Entity
@Table
@Check(constraints = "bird_type = 'turkey' OR bird_type = 'duck' OR bird_type = 'quail' OR bird_type = 'chicken'")
//turkey, duck, quail, chicken(индюк, утка, перепелка, курица)
public class BirdEntity {

    @Id
    @GeneratedValue
    private long Id;
    @Column(name = "bird_type")
    private String birdType;
    @Column
    private int totalWeight;
    @Column
    private int pricePerKg;

    public BirdEntity(String birdType, int totalWeight) {
        this.birdType = birdType;
        this.totalWeight = totalWeight;
    }

    public BirdEntity(String birdType, int totalWeight, int pricePerKg) {
        this.birdType = birdType;
        this.totalWeight = totalWeight;
        this.pricePerKg = pricePerKg;
    }

    public BirdEntity() {
    }
}
