package com.rentacar.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputBody {

    public Long id;
    public String brand;
    public String model;
    public Boolean is_available;
    public Long user_id;

}
