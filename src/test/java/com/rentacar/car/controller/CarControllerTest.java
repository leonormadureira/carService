package com.rentacar.car.controller;

import com.google.gson.Gson;
import com.rentacar.car.domain.Car;
import com.rentacar.car.dto.InputBody;
import com.rentacar.car.service.serviceImplementation.CarServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarControllerTest {

    public MockMvc mock;
    public Car car = new Car();
    public List<Car> listOfCars = new ArrayList<Car>();
    public InputBody inputBody = new InputBody();
    public Gson gson = new Gson();
    public String json = gson.toJson(inputBody);

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    CarServiceImplementation mockService;

    @Before
    public void setUp() {

        mock = MockMvcBuilders.webAppContextSetup(this.wac).build();

        car.setId(Long.valueOf(1));
        car.setBrand("Citroen");
        car.setModel("Picasso");
        car.setIs_available(true);
        car.setUser_id(null);
        listOfCars.add(car);

        inputBody.setId(car.getId());
        inputBody.setBrand(car.getBrand());
        inputBody.setModel(car.getModel());
        inputBody.setIs_available(car.getIs_available());
        inputBody.setUser_id(car.getUser_id());

    }


    @Test
    public void createCarTest()throws Exception {

        mock.perform(post("/carService").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(car);

    }

    @Test
    public void getCarTest()throws Exception {

        Mockito.when(mockService.getCar(anyLong(),anyBoolean())).thenReturn(listOfCars);
        mock.perform(get("/carService").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    public void getCarsByUserIdTest()throws Exception {

        Mockito.when(mockService.getCarsByUserId(anyLong())).thenReturn(listOfCars);
        mock.perform(get("/carService/searchCars").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

    }

    @Test
    public void deleteCarTest()throws Exception {

        mock.perform(delete("/carService").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

    }

    @Test
    public void bookCarTest()throws Exception {

        mock.perform(post("/carService/rentcar").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(car);

    }

    @Test
    public void releaseCarTest()throws Exception {

        mock.perform(post("/carService/releasecar").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(car);

    }
}
