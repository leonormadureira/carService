package com.rentacar.car.service.serviceImplementation;


import com.rentacar.car.domain.Car;
import com.rentacar.car.dto.InputBody;
import com.rentacar.car.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CarServiceImplementation {

    private CarRepository repository;
    public Long id;
    public String brand;
    public String model;
    public boolean is_available;
    public Long user_id;

    @Autowired
    public CarServiceImplementation(CarRepository repository) {
        this.repository = repository;
    }

    public Car createCar(InputBody carDto) {

        Car newCar = new Car();

        newCar.setIs_available(true);
        newCar.setBrand(carDto.getBrand());
        newCar.setModel(carDto.getModel());

        Car savedCar = repository.save(newCar);

        log.info(String.valueOf(savedCar));

        return savedCar;

    }

    public List<Car> getCar (Long id, boolean is_available) {

        if (id != null) {

            List<Car> list_cars_found_by_id = repository.findByCarId(id);

            if (list_cars_found_by_id.size() == 0) {

                log.error("No cars found with id: " + id);

                return repository.findByCarId(id);

            } else {

                log.info(String.valueOf(list_cars_found_by_id));

                return list_cars_found_by_id;
            }

        } else {

            List<Car> list_cars_found_by_availability = repository.findByIs_available(is_available);

            if (list_cars_found_by_availability.size() == 0) {

                log.error("No cars found with availability: " + is_available);

            } else {

                log.info(String.valueOf(list_cars_found_by_availability));
            }

            return list_cars_found_by_availability;

        }

    }

    public List<Car> getCarsByUserId(Long user_id){

        List<Car> carsRentedByUserId = repository.findCarsByUserId(user_id);

        if (carsRentedByUserId.size() == 0){

            log.error("User_id: " + user_id + " is not renting any cars at the moment.");

            return null;
        }

        return repository.findCarsByUserId(user_id);

    }

    public void deleteCar(Long id) {

        if (id == null) {
            log.error("No id provided.");
        }

        List<Car> list_cars_to_delete = repository.findByCarId(id);

        if (list_cars_to_delete.size() == 0) {
            log.error("No cars found with id: " + id);
        }

        for (Car car_to_delete : list_cars_to_delete) {

            log.info(car_to_delete + " will be deleted.");
            repository.delete(car_to_delete);

        }
    }

    public Car bookCar(Long id, Long user_id) {

        List<Car> list_cars_found_by_id = repository.findByCarId(id);

        if (list_cars_found_by_id.size() == 0) {

            log.error("No cars found with id: " + id + ". Please choose another car.");

            return null;

        } else {

            Car car = list_cars_found_by_id.get(0);

            if (car.getIs_available()) {

                car.setIs_available(false);
                car.setUser_id(user_id);

                log.info(String.valueOf(car));

                return repository.save(car);

            } else

                log.error("Selected car is not available. Please choose another car.");

            return car;
        }
    }

    public Car releaseCar(Long id) {

        List<Car> list_cars_found_by_id = repository.findByCarId(id);

        if (list_cars_found_by_id.size() == 0) {

            log.error("No cars found with id: " + id + ". Please choose another car.");

            return null;

        } else {

            Car car = list_cars_found_by_id.get(0);

            if (car.getIs_available()){

                log.error("The selected car is not rented at the moment. Please choose another car.");

                return null;

            } else

            car.setIs_available(true);
            car.setUser_id(null);

            return repository.save(car);
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
