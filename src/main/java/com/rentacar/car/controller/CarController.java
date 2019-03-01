package com.rentacar.car.controller;

import com.rentacar.car.domain.Car;
import com.rentacar.car.dto.CarDto;
import com.rentacar.car.service.serviceImplementation.CarServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarServiceImplementation service;

    @Autowired
    public CarController(CarServiceImplementation service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/carService")
    public Car createCar(@RequestBody CarDto carDto){

        return service.createCar(carDto);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/carService")
    public List<Car> getCar(@RequestParam Long id, boolean is_available){

        return service.getCar(id, is_available);

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/carService")
    public ResponseEntity deleteCar(@RequestParam Long id){

        service.deleteCar(id);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/carService/bookcar")
    public Car bookACar(@RequestParam Long id, Long user_id){

        return service.bookCar(id, user_id);

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/carService/releasecar")
    public Car releaseCar(@RequestParam Long id){

        return service.releaseCar(id);

    }

}
