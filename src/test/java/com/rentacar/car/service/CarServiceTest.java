package com.rentacar.car.service;

import com.rentacar.car.domain.Car;
import com.rentacar.car.dto.CarDto;
import com.rentacar.car.dto.InputBody;
import com.rentacar.car.repository.CarRepository;
import com.rentacar.car.service.serviceImplementation.CarServiceImplementation;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    public List<Car> listWithFreeCar = new ArrayList<Car>();
    public List<Car> listWithRentedCar = new ArrayList<Car>();
    public Car freeCar = new Car();
    public Car rentedCar = new Car();

    @MockBean
    private CarRepository repository;

    @Autowired
    private CarServiceImplementation service;

    private Long idExists = Long.valueOf(6);

    @Before
    public void setUp() throws Exception {

        freeCar.setIs_available(true);
        freeCar.setId(Long.valueOf(1));
        freeCar.setModel("Ibiza");
        freeCar.setBrand("Seat");
        repository.save(freeCar);
        listWithFreeCar.add(freeCar);

        rentedCar.setIs_available(false);
        rentedCar.setId(Long.valueOf(2));
        rentedCar.setModel("Panda");
        rentedCar.setBrand("Fiat");
        rentedCar.setUser_id(Long.valueOf(2));
        repository.save(rentedCar);
        listWithRentedCar.add(rentedCar);

        //System.out.println("\nMock: " + atLeastOneCarList);

        Mockito.when(this.repository.findByCarId(idExists)).thenReturn(listWithFreeCar);
        Mockito.when(this.repository.findByCarId(Long.valueOf(1))).thenReturn(listWithRentedCar);
        Mockito.when(this.repository.findByIs_available(true)).thenReturn(listWithFreeCar);
        Mockito.when(this.repository.save(Mockito.any())).thenReturn(freeCar);
    }

    public void createCarTest() {

        InputBody inputCar = new InputBody();

        inputCar.setBrand("Citroen");
        inputCar.setModel("C1");

        Car newCar = service.createCar(inputCar);

        Assert.assertNotNull(newCar);
        Assert.assertNotNull(newCar.getId());
        Assert.assertEquals(newCar.getBrand(), "Citroen");
        Assert.assertEquals(newCar.getModel(), "C1");
        Assert.assertTrue(newCar.getIs_available());
        Assert.assertNull(newCar.getUser_id());

    }

    @Test
    public void getCarByExistentIdTest() {

        List<Car> carList = service.getCar(idExists, false);

        Assert.assertThat(carList, is(not(empty())));

    }

    @Test
    public void getCarByNonExistentIdTest() {

        List<Car> carList = service.getCar(Long.valueOf(4), false);

        Assert.assertThat(carList, is(empty()));

    }

    @Test
    public void getCarByAvailabilityCarsNotAvailableTest() {

        List<Car> carList = service.getCar(null, false);

        Assert.assertThat(carList, is(empty()));

    }

    @Test
    public void getCarByAvailabilityCarsAvailableTest() {

        List<Car> carList = service.getCar(null, true);

        Assert.assertThat(carList, is(not(empty())));

        for (Car car : carList) {
            Assert.assertTrue(car.getIs_available());

        }

    }

    @Test
    public void bookCarTest() {

        Car bookedCar = service.bookCar(idExists, Long.valueOf(1));

        Assert.assertNotNull(bookedCar);
        Assert.assertFalse(bookedCar.getIs_available());
        Assert.assertEquals(bookedCar.getUser_id(), Long.valueOf(1));

    }

    @Test
    public void releaseCarTest() {

        Car releasedCar = service.releaseCar(Long.valueOf(1));

        Assert.assertNotNull(releasedCar);
        Assert.assertTrue(releasedCar.getIs_available());
        Assert.assertNull(releasedCar.getUser_id());

    }

}
