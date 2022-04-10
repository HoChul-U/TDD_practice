package com.nhnacademy.hochul.user;

import static com.nhnacademy.hochul.user.UserType.PAYCO;
import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.hochul.car.Car;
import com.nhnacademy.hochul.parking.EnterParkingLot;
import com.nhnacademy.hochul.parking.ExitParkingLot;
import com.nhnacademy.hochul.parking.ParkingLot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("Payco 회원인지 확인 맞으면 True 틀리면 False")
    @Test
    void checkPaycoMemberTest() {
        User user = new User(PAYCO, 1_0000L);
        User user2 = new User(1_0000L);
        ExitParkingLot exitParkingLot = new ExitParkingLot();

        assertThat(exitParkingLot.barcode(user)).isTrue();
        assertThat(exitParkingLot.barcode(user2)).isFalse();

    }

    @DisplayName("User 에 coupon 개념 추가")
    @Test
    void couponTest() {
        User user1 = new User(PAYCO, 1_0000L, 2);
        Car car1 = new Car("1111", user1);
        User user2 = new User(PAYCO, 1_0000L, 1);
        Car car2 = new Car("1111", user1);
        ParkingLot parkingLot = new ParkingLot();
        EnterParkingLot enterParkingLot = new EnterParkingLot();
        ExitParkingLot exitParkingLot = new ExitParkingLot();

        enterParkingLot.scan(parkingLot.getRepository(), car1);
//        //쿠폰 자리수에서 시간값 *60 해서 계산
        assertThat(exitParkingLot.checkCoupon(user1.getCoupon())).isEqualTo(120);

        exitParkingLot.exit(parkingLot.getRepository(),car1,180);
        assertThat(user1.getMoney()).isEqualTo(9100L);

        enterParkingLot.scan(parkingLot.getRepository(), car2);

        exitParkingLot.exit(parkingLot.getRepository(), car2, 59);
        assertThat(user2.getMoney()).isEqualTo(10000L);
    }

}
