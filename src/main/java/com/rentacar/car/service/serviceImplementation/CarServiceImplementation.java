package com.rentacar.car.service.serviceImplementation;


import com.rentacar.car.domain.Car;
import com.rentacar.car.dto.CarDto;
import com.rentacar.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImplementation {

    private final CarRepository repository;
    public Long id;
    public String brand;
    public String model;
    public boolean is_available;
    public Long user_id;

    @Autowired
    public CarServiceImplementation(CarRepository repository) {
        this.repository = repository;
    }

    public Car createCar(CarDto carDto) {

        Car newCar = new Car();

        newCar.setIs_available(true);
        newCar.setBrand(carDto.getBrand());
        newCar.setModel(carDto.getModel());

        repository.save(newCar);

        System.out.println(newCar);
        return newCar;

    }

    public List<Car> getCar(Long id, boolean is_available) {

        if (id != null) {

            List<Car> list_cars_found_by_id = repository.findByCarId(id);

            if (list_cars_found_by_id.size() == 0) {

                System.out.println("\nNo cars found with id: " + id);

            } else {

                System.out.println(list_cars_found_by_id);
            }

            return list_cars_found_by_id;

        } else {

            List<Car> list_cars_found_by_availability = repository.findByIs_available(is_available);

            if (list_cars_found_by_availability.size() == 0) {

                System.out.println("\nNo cars found with availability: " + is_available);

            } else {

                System.out.println(list_cars_found_by_availability);
            }

            return list_cars_found_by_availability;

        }

    }

    public List<Car> getCarsByUserId(Long user_id){

        return repository.findCarsByUserId(user_id);

    }

    public void deleteCar(Long id) {

        if (id == null) {
            System.out.println("\nNo id provided.");
        }

        List<Car> list_cars_to_delete = repository.findByCarId(id);

        if (list_cars_to_delete.size() == 0) {
            System.out.println("\nNo cars found with id: " + id);
        }

        for (Car car_to_delete : list_cars_to_delete) {

            System.out.println(car_to_delete);
            repository.delete(car_to_delete);

        }
    }

    public Car bookCar(Long id, Long user_id) {

        List<Car> list_car_found_by_id = repository.findByCarId(id);

        Car car = list_car_found_by_id.get(0);

        car.setIs_available(false);
        car.setUser_id(user_id);

        return repository.save(car);

    }

    public Car releaseCar(Long id) {

        List<Car> list_car_found_by_id = repository.findByCarId(id);

        Car car = list_car_found_by_id.get(0);

        car.setIs_available(true);
        car.setUser_id(null);

        return repository.save(car);

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
