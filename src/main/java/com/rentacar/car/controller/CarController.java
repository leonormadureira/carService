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
    public Car createCar(@RequestBody InputBody requestBodyRequest){

        return service.createCar(requestBodyRequest);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/carService")
    public List<Car> getCar(@RequestBody InputBody requestBodyRequest){

        return service.getCar(requestBodyRequest.getId(), requestBodyRequest.getIs_available());

    }

    @RequestMapping(method = RequestMethod.GET, path = "/carService/searchCars")
    public List<Car> getCarsByUserId(@RequestBody InputBody requestBodyRequest){

        return service.getCarsByUserId(requestBodyRequest.getUser_id());

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/carService")
    public ResponseEntity deleteCar(@RequestBody InputBody requestBodyRequest){

        service.deleteCar(requestBodyRequest.getId());

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/carService/rentcar")
    public Car bookCar(@RequestBody InputBody requestBodyRequest){

        return service.bookCar(requestBodyRequest.getId(), requestBodyRequest.getUser_id());

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/carService/releasecar")
    public Car releaseCar(@RequestBody InputBody requestBodyRequest){

        return service.releaseCar(requestBodyRequest.getId());

    }

}
