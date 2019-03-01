package com.rentacar.car.service;

import com.rentacar.car.domain.Car;
import com.rentacar.car.dto.CarDto;

import java.util.List;

public interface CarService {

    /**
     * Create the record of a new car.
     *
     * @param carDto
     * @return Car
     */
    Car createCar(CarDto carDto);

    /**
     * Search for a specific car by its id or search by its availability.
     *
     * @param id, is_available
     * @return List<Car>
     */
    List<Car> getCar(Long id, boolean is_available);

    /**
     * Delete the record of specific car by its id.
     *
     * @param id
     * @return void
     */
    void deleteCar(Long id);

    /**
     * Update the car's availability and the user who's renting it.
     *
     * @param id, is_available, user_id
     * @return Car
     */
    Car updateCar(Long id, boolean is_available, Long user_id);


}
