package com.rentacar.car.repository;

import com.rentacar.car.domain.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    public CarRepository repository;

    Car car1 = new Car();
    Car car2 = new Car();
    Car car3 = new Car();

    @Before
    public void setUp()throws Exception {

        car1.setId(Long.valueOf(1));
        car1.setBrand("Citroen");
        car1.setModel("Picasso");
        car1.setIs_available(true);
        car1.setUser_id(null);
        repository.save(car1);

        car2.setId(Long.valueOf(2));
        car2.setBrand("VW");
        car2.setModel("Polo");
        car2.setIs_available(false);
        car2.setUser_id(Long.valueOf(1));
        repository.save(car2);

        car3.setId(Long.valueOf(3));
        car3.setBrand("Hyundai");
        car3.setModel("Getz");
        car3.setIs_available(false);
        car3.setUser_id(Long.valueOf(4));
        repository.save(car3);

    }

    @Test
    public void findByCarIdTest(){

        List<Car> listOfCars = repository.findByCarId(Long.valueOf(3));

        for (Car car : listOfCars) {

            Assert.assertEquals(Long.valueOf(3), car.getId());
        }

    }

    @Test
    public void findCarsByUserIdTest(){

        List<Car> listOfCars = repository.findCarsByUserId(Long.valueOf(1));

        for (Car car : listOfCars) {

            Assert.assertEquals(Long.valueOf(1), car.getUser_id());
        }

    }

    @Test
    public void findByIs_availableTest(){

        List<Car> listOfCars = repository.findByIs_available(false);

        for (Car car : listOfCars){

        Assert.assertFalse(car.getIs_available());

        }

    }

}
