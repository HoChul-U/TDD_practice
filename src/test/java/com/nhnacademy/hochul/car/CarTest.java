package com.nhnacademy.hochul.car;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.hochul.parking.ParkingLot;
import com.nhnacademy.hochul.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @DisplayName("Car 에 번호판이랑 돈이 잘 들어갔는지 확인")
    @Test
    void carTest() {
        User user = new User(1_000L);
        Car car = new Car("0001", user);
        assertThat(car.getNumber()).isEqualTo("0001");
        assertThat(car.getUserMoney()).isEqualTo(1_000L);

    }
    @DisplayName("번호판 으로 차량확인")
    @Test
    void parkingAreaScanTest() {
        User user = new User(1_000L);
        Car car = new Car("0001",user);
        ParkingLot parkingArea = new ParkingLot();
        parkingArea.scan(car);

        assertThat(parkingArea.getRepository().get("0001")).isEqualTo(car);
    }
}
