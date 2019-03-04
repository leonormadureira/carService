package com.rentacar.car.repository;

import com.rentacar.car.domain.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository <Car, Long> {

    @Query(value = "SELECT * FROM Car WHERE is_available = ?1", nativeQuery = true)
    List<Car> findByIs_available(boolean is_available);

    @Query(value = "SELECT * FROM Car WHERE id = ?1", nativeQuery = true)
    List<Car> findByCarId(Long id);

    @Query(value = "SELECT * FROM Car WHERE user_id = ?1", nativeQuery = true)
    List<Car> findCarsByUserId(Long id);

}
