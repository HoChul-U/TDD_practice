package com.nhnacademy.hochul.exception;

import static com.nhnacademy.hochul.car.CarType.트럭;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.hochul.car.Car;
import com.nhnacademy.hochul.parking.EnterParkingLot;
import com.nhnacademy.hochul.parking.ExitParkingLot;
import com.nhnacademy.hochul.parking.ParkingLot;
import com.nhnacademy.hochul.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExceptionTest {
    @DisplayName("돈이 0 원이하로 떨어지면 Exception")
    @Test
    void moneyNegativeExceptionTest() {
        User user = new User(-1);
        Car car = new Car("0001", user);
        assertThatThrownBy(car::getUserMoney)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Negative");
    }

    @DisplayName("주차장에 대형차(트럭 등)은 주차할 수 없습니다.")
    @Test
    void canNotParkingExceptionTest() {
        ParkingLot parkingLot = new ParkingLot();
        EnterParkingLot enterParkingLot = new EnterParkingLot();
        ExitParkingLot exitParkingLot = new ExitParkingLot();
        User user = new User(1_000L);
        Car car = new Car("1111", user, 트럭);

        assertThat(car.getCarType()).isEqualTo(트럭);

        assertThatThrownBy(() -> enterParkingLot.scan(parkingLot.getRepository(), car))
            .isInstanceOf(VehicleIsNotValid.class)
            .hasMessageContaining("not allowed");

    }

    @DisplayName("주차장에서 가격지불시 돈이모자랄경우 예외발생")
    @Test
    void payFeeException() {
        ParkingLot parkingLot = new ParkingLot();
        User user = new User(1_000L);
        Car car = new Car("1111", user);
        ExitParkingLot exitParkingLot = new ExitParkingLot();
        assertThatThrownBy(() -> exitParkingLot.exit(parkingLot.getRepository(), car, 1440))
            .isInstanceOf(NotEnoughMoney.class)
            .hasMessageContaining("don't have Money");
    }
}
