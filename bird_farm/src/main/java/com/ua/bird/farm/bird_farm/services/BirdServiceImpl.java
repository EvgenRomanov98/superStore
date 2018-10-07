package com.ua.bird.farm.bird_farm.services;


import com.ua.bird.farm.bird_farm.dao.BirdEntity;
import com.ua.bird.farm.bird_farm.dao.repository.BirdRepository;
import com.ua.bird.farm.bird_farm.dto.BirdDto;
import com.ua.bird.farm.bird_farm.dto.BirdListDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// duckPrice
// for 10 kg - price not change
// from 10 to 30 - price = price -1 per unit;
// from 30 to 50 - price = price -2 per unit;
@Service
@Transactional
@Log4j2
public class BirdServiceImpl implements BirdService {

    @Autowired
    private BirdRepository birdRepository;

    @Value("${duck.price}")
    private int duckPrice;
    @Value("${chicken.price}")
    private String chickenPrice;
    @Value("${turkey.price}")
    private String turkeyPrice;
    @Value("${quail.price}")
    private String quailPrice;


    //Todo Возможные типы птиц: индюк, утка, перепелка, курица.  Ограничить типы птиц либо кодом(возможно перечисления) либо базой данных(хард левел)
    //Todo сделать АПИ->Сервис который выводит все типы птиц, их остаток, цену за штуку и систеу скидок на каждый тип,
    //Todo вывести эти данные в джжейсоне

    //Todo переделать метод getBirdByTypeAndTotalWeight, прежде чем вывести информацию по запросу ,
    //Todo посмотреть наличие остатка и сранить запрашиваем количеством, если есть необходимый объем выдать его, а остаток уменьшить если нет броса исключение
    //Todo Продумать механизм общения по данному апи фермы и магазина

    //Todo переделать сервис расчета цены который зависит от типа птиц и уменьшать цену в процентах

    //Todo просчитать тотал сам?

    //Todo обратится к мастер гуглу,  ознакомится с bigDecimal, реализовать примеры.
    //Todo http://tutorials.jenkov.com/java-json/jackson-objectmapper.html пройти тутор повторить все примеры+усложнить.

    //Todo повторить кастомные Exception

    // Дабавить добавление птиц с интерфейсв
    // добавить информацию о ферме(птици, цена,какиескидки)
    @Override
    public BirdDto getBirdByTypeAndTotalWeight(String type, int weight) {
        BirdDto birdDto = null;
        BirdEntity birdEntity = null;
        log.info("------------ service getBirdByTypeAndTotalWeight 1");
        try {
            birdEntity = birdRepository.findAllByBirdType(type);
            log.info(birdEntity);
            log.info("------------ service getBirdByTypeAndTotalWeight 2");
            if (birdEntity.getTotalWeight() < weight) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("------------ service getBirdByTypeAndTotalWeight 3");
        //  1. Создать  ДТО для ответа
        birdDto = new BirdDto();
        //  2. Создать конвертар цен от условия
        try {
            log.info("------------ service getBirdByTypeAndTotalWeight 4 {}", birdEntity);
            birdDto.setPricePerKg(checkPriceByWeight(birdEntity, weight));
            birdDto.setTotalWeight(weight);
            birdDto.setTypeBird(birdEntity.getBirdType());

            //  3. Засетить Дто и отдать
            log.info("------------ service getBirdByTypeAndTotalWeight 5");
            birdEntity.setTotalWeight(birdEntity.getTotalWeight() - weight);
            birdRepository.saveAndFlush(birdEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return birdDto;
    }

    private int checkPriceByWeight(BirdEntity birdEntity, int weigth) {
        int price = birdEntity.getPricePerKg();
        if (weigth >= 10 && weigth < 30) {
            price--;
        } else if (birdEntity.getTotalWeight() >= 30) {
            price -= 2;
        }
        return price;
    }

    public List<BirdDto> getAllBirdWithSale() {

        List<BirdEntity> birds = new ArrayList<>(birdRepository.findAll());
        List<BirdDto> birdList = new ArrayList<>();

        for (BirdEntity be : birds) {// идем по массиву птиц из бд

            BirdDto birdDto = new BirdDto(be.getBirdType(), be.getTotalWeight(), be.getPricePerKg(), saleFrom10to30kg(be), saleFrom30kg(be));

            birdList.add(birdDto);
        }


        return birdList;// отдаем мапу с птицами
    }

    private int saleFrom10to30kg(BirdEntity be) {//скидка за покупку от 10 до 30 кг
        return be.getPricePerKg() - 1;
    }

    private int saleFrom30kg(BirdEntity be) {//скидка за покупку от 30 грн
        return be.getPricePerKg() - 2;
    }

    public void addBird(BirdDto birdDto) {
        try {
            System.out.println(birdDto.toString());
            if (birdDto.getTypeBird() != null) {
                ArrayList<BirdEntity> birdEntity = new ArrayList<>(birdRepository.findAll());
                log.info("----------service add Bird 1 {}", birdDto.toString());
                flag:
                for (BirdEntity be : birdEntity) {
                    if (be.getBirdType().equals(birdDto.getTypeBird())) {
                        log.info("----------service add Bird 2 {}", birdDto.toString());
                        be.setTotalWeight(be.getTotalWeight() + birdDto.getTotalWeight());
                        be.setPricePerKg(birdDto.getPricePerKg());
                        birdRepository.saveAndFlush(be);
                        break flag;
                    }

                    log.info("----------service add Bird 3 {}");
                }
                log.info("----------service add Bird 4 {}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BirdListDto showAll() {
        ArrayList<BirdEntity> birdEntity = new ArrayList<>(birdRepository.findAll());
        BirdListDto birdListDto = new BirdListDto(new ArrayList<>());
        for (BirdEntity be : birdEntity) {
            birdListDto.getBirdDtos().add(new BirdDto(be.getBirdType(), be.getTotalWeight(), be.getPricePerKg(), saleFrom10to30kg(be), saleFrom30kg(be)));
        }
        return birdListDto;
    }
}


