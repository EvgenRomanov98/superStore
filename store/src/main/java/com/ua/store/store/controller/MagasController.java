package com.ua.store.store.controller;

import com.ua.store.store.services.GetBirdFromFarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("magaz")
public class MagasController {

    @Autowired
    private GetBirdFromFarmServices getBirdFromFarmServices;

    @GetMapping("/one")
    public void magas() {
        getBirdFromFarmServices.getBirdFromFar();
    }

    @GetMapping("/sec")
    public void magas2() {
        getBirdFromFarmServices.getBirdFromFar2();
    }

    @GetMapping("three")
    public void magas3() {
        getBirdFromFarmServices.getBirdFromFar3();
    }

    @GetMapping("four")
    public void magas4() {
        getBirdFromFarmServices.getBirdFromFar4();
    }
}
