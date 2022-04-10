package com.nhnacademy.hochul.parking;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.hochul.car.Car;
import com.nhnacademy.hochul.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExitParkingLotTest {
    @DisplayName("ExitParkingLot (주차장입구) 에 차량이 나갈시 Map 에 삭제")
    @Test
    void exitParkingLotTest() {
        ParkingLot parkingLot = new ParkingLot();
        User user = new User(1_0000L);
        Car car = new Car("1111", user);
        parkingLot.scan(car);

        ExitParkingLot exitParkingLot = new ExitParkingLot();
        exitParkingLot.exit(parkingLot.getRepository(), car, 0);
        assertThat(parkingLot.getRepository().get(car.getNumber())).isNull();

    }

    @DisplayName("ExitParkingLot (주차장입구) 에 차량 나갈시 계산")
    @Test
    void exitParkingLotPayTest() {
        ParkingLot parkingLot = new ParkingLot();
        User user = new User(1_0000L);
        Car car = new Car("1111", user);
        parkingLot.scan(car);

        ExitParkingLot exitParkingLot = new ExitParkingLot();
        exitParkingLot.exit(parkingLot.getRepository(), car, 30);

        assertThat(car.getUserMoney()).isEqualTo(1_0000L);

    }
}
