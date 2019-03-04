package com.rentacar.car.service;

import com.rentacar.car.domain.Car;
import com.rentacar.car.dto.CarDto;

import java.util.List;

public interface CarService {

    /**
     * Create the record of a new car.
     *
     * @param carDto - car data such: brand and model
     * @return Car - car data such: brand and model. id of the car is generated automatically. is_available is set to true. user_id is null.
     */
    Car createCar(CarDto carDto);

    /**
     * Search for a specific car by its id or search by its availability.
     * If id is passed as input, is_available is ignored, and the data of the car with that id is returned.
     * If id is null, a search is done for all cars by the availability provided as input.
     *
     * @param id - id of the car. If null,
     * @param is_available - status of car's availability. If id is passed as input, is_available is ignored
     * @return List<Car>
     */
    List<Car> getCar(Long id, boolean is_available);

    /**
     * Delete the record of specific car by its id.
     *
     * @param id
     */
    void deleteCar(Long id);

    /**
     * Allows a user to book a car.
     * Updates the car's availability and the user who's renting it.
     *
     * @param id - id of the car
     * @param user_id
     * @return Car
     */
    Car bookCar(Long id, Long user_id);

    /**
     * Allows a car reservation to be released.
     * Update the car's availability and removes the user who's rented it.
     *
     * @param id
     * @return Car
     */
    Car releaseCar(Long id);

}
