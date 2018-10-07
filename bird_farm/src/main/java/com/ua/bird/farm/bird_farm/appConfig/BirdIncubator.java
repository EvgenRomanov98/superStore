package com.ua.bird.farm.bird_farm.appConfig;


import com.ua.bird.farm.bird_farm.dao.BirdEntity;
import com.ua.bird.farm.bird_farm.dao.repository.BirdRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Log4j2
public class BirdIncubator {

    @Autowired
    private BirdRepository birdRepository;

    @Value("${duck.price}")
    private int pricePerKgDuck;
    @Value("${chicken.price}")
    private int pricePerKgChicken;
    @Value("${turkey.price}")
    private int pricePerKgTurkey;
    @Value("${quail.price}")
    private int pricePerKgQuail;

    @PostConstruct
    public void incubator() {
        log.info("------------ incubator");
        try {

            BirdEntity duck = new BirdEntity("duck", 25, pricePerKgDuck);
            birdRepository.save(duck);

            BirdEntity chicken = new BirdEntity("chicken", 30, pricePerKgChicken);
            birdRepository.save(chicken);

            BirdEntity turkey = new BirdEntity("turkey", 35, pricePerKgTurkey);
            birdRepository.save(turkey);

            BirdEntity quail = new BirdEntity("quail", 40, pricePerKgQuail);
            birdRepository.save(quail);

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("------------ incubator FINISH");

    }
}
