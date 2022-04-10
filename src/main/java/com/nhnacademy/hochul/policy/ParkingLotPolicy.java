package com.nhnacademy.hochul.policy;

import com.nhnacademy.hochul.car.Car;

public interface ParkingLotPolicy {
    void payFee(Car car, int time);
}
