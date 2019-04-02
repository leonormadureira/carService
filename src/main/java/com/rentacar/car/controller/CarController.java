package com.rentacar.car.controller;

import com.rentacar.car.domain.Car;
import com.rentacar.car.dto.InputBody;
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
    public Car createCar(@RequestBody InputBody requestBody){

        return service.createCar(requestBody);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/carService")
    public List<Car> getCar(@RequestParam Long id, Boolean is_available){

        return service.getCar(id, is_available);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/carService/searchCars")
    public List<Car> getCarsByUserId(@RequestParam Long user_id){

        return service.getCarsByUserId(user_id);

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/carService")
    public ResponseEntity deleteCar(@RequestBody InputBody requestBody){

        service.deleteCar(requestBody.getId());

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/carService/rentcar")
    public Car bookCar(@RequestBody InputBody requestBody) throws IllegalArgumentException {

        return service.bookCar(requestBody.getId(), requestBody.getUser_id());

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/carService/releasecar")
    public Car releaseCar(@RequestBody InputBody requestBody){

        return service.releaseCar(requestBody.getId());

    }

}
