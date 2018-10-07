package com.ua.bird.farm.bird_farm.appConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class Config {

    @Autowired
    private EntityManager em;

}
