package com.nhnacademy.hochul.parking;

import static com.nhnacademy.hochul.car.CarType.트럭;

import com.nhnacademy.hochul.car.Car;
import com.nhnacademy.hochul.exception.VehicleIsNotValid;
import java.util.Map;

public class EnterParkingLot {
    public void scan(Map<String,Car> map , Car car) {
        if(car.getCarType()==트럭) throw new VehicleIsNotValid(car.getCarType());
        map.put(car.getNumber(),car);
    }
}
