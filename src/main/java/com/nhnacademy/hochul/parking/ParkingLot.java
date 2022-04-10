package com.nhnacademy.hochul.parking;

import com.nhnacademy.hochul.car.Car;
import com.nhnacademy.hochul.policy.ParkingLotFeeVersion1;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot  {
    private final ParkingLotFeeVersion1 policy = new ParkingLotFeeVersion1();

    private final Map<String, Car> carRepository = new HashMap<>();

    public void scan(Car car) {
        carRepository.put(car.getNumber(),car);
    }

    public Map<String,Car> getRepository() {
        return carRepository;
    }

    public void parkNum(Car car ,String areaNumber) {
        car.setParkingNumber(areaNumber);
    }

    public ParkingLotFeeVersion1 getPolicy() {
        return policy;
    }
}
