package com.nhnacademy.hochul.exception;

import com.nhnacademy.hochul.car.CarType;

public class VehicleIsNotValid extends IllegalArgumentException {
    public VehicleIsNotValid(CarType carType) {
        super("This car is not allowed park here. CarType : " + carType);
    }
}
