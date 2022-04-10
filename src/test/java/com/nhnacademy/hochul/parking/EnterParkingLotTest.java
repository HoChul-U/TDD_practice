package com.nhnacademy.hochul.parking;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.hochul.car.Car;
import com.nhnacademy.hochul.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnterParkingLotTest {

    @DisplayName("EnterParkingLot (주차장입구) 에 차량이 들어오면 Map 에 저장")
    @Test
    void enterParkingLotAndExitParkingLotTest() {
        ParkingLot parkingLot = new ParkingLot();
        User user = new User(10_000L);
        Car car2 = new Car("2222", user);

        EnterParkingLot enterParkingLot = new EnterParkingLot();

        //입구생성
        enterParkingLot.scan(parkingLot.getRepository(), car2);
        assertThat(parkingLot.getRepository().get(car2.getNumber()).getUserMoney()).isEqualTo(
            car2.getUserMoney());

    }
}
