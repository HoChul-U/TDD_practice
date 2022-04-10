package com.nhnacademy.hochul.parking;

import static com.nhnacademy.hochul.car.CarType.경차;
import static com.nhnacademy.hochul.user.UserType.PAYCO;
import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.hochul.car.Car;
import com.nhnacademy.hochul.user.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkingServiceTest {
    @DisplayName("주차장에 차량이 들어갔는지 확인")
    @Test
    void ParkingAreaTest() {
        User user = new User(1_000L);
        Car car1 = new Car("0001", user);

        ParkingLot parkingArea = new ParkingLot();
        parkingArea.scan(car1);

        assertThat(parkingArea.getRepository().get("0001").getUserMoney()).isEqualTo(1_000L);
    }

    @DisplayName("주차장 번호부여")
    @Test
    void ParkingNumberTest() {
        User user = new User(1_000L);
        Car car = new Car("0001", user);

        ParkingLot parkingArea = new ParkingLot();
        parkingArea.scan(car);
        parkingArea.parkNum(car, "A-1");

        assertThat(car.getParkingNumber()).isEqualTo("A-1");

    }

    @Disabled("payFee 를 Exit 할때 부여하여 사용안함")
    @DisplayName("주차요금 부여")
    @Test
    void parkingAreaFeeTest() {
        User user = new User(10_000L);
        Car car = new Car("0001", user);
        ParkingLot parkingArea = new ParkingLot();
        parkingArea.scan(car);

        parkingArea.getPolicy().payFee(car, 30);
        assertThat(parkingArea.getRepository().get("0001").getUserMoney()).isEqualTo(9_000L);

        parkingArea.getPolicy().payFee(car, 31);
        assertThat(parkingArea.getRepository().get("0001").getUserMoney()).isEqualTo(7_500L);

        parkingArea.getPolicy().payFee(car, 50);
        assertThat(parkingArea.getRepository().get("0001").getUserMoney()).isEqualTo(5_500L);

        parkingArea.getPolicy().payFee(car, 61);
        assertThat(parkingArea.getRepository().get("0001").getUserMoney()).isEqualTo(2_500L);

        Car car2 = new Car("0002", user);
        parkingArea.scan(car2);

        parkingArea.getPolicy().payFee(car2, 360);
        assertThat(parkingArea.getRepository().get("0002").getUserMoney()).isZero();

    }





    @DisplayName("주차장 요금 무료(요금표가 변경되었습니다.) 최초 30분")
    @Test
    void parkingLotPayTestFree() {
        ParkingLot parkingLot = new ParkingLot();
        EnterParkingLot enterParkingLot = new EnterParkingLot();
        ExitParkingLot exitParkingLot = new ExitParkingLot();
        User user = new User(10_000L);
        Car car = new Car("1111", user);

        enterParkingLot.scan(parkingLot.getRepository(), car);
        exitParkingLot.exit(parkingLot.getRepository(), car, 30);
        assertThat(car.getUserMoney()).isEqualTo(10_000L);
    }

    @DisplayName("주차장 요금 무료(요금표가 변경되었습니다.) 최초 30분 ~ 1시간")
    @Test
    void parkingLotPayTestThousandWon() {
        ParkingLot parkingLot = new ParkingLot();
        EnterParkingLot enterParkingLot = new EnterParkingLot();
        ExitParkingLot exitParkingLot = new ExitParkingLot();
        User user = new User(1_0000L);
        Car car = new Car("1111", user);

        enterParkingLot.scan(parkingLot.getRepository(), car);
        exitParkingLot.exit(parkingLot.getRepository(), car, 40);
        assertThat(car.getUserMoney()).isEqualTo(9_000L);
    }

    @DisplayName("주차장 요금 무료(요금표가 변경되었습니다.) 최초 30분 ~ 1시간")
    @Test
    void parkingLotPayTest_TenMoreMinute() {
        ParkingLot parkingLot = new ParkingLot();
        EnterParkingLot enterParkingLot = new EnterParkingLot();
        ExitParkingLot exitParkingLot = new ExitParkingLot();
        User user = new User(1_0000L);
        Car car = new Car("1111", user);

        enterParkingLot.scan(parkingLot.getRepository(), car);
        exitParkingLot.exit(parkingLot.getRepository(), car, 61);
        assertThat(car.getUserMoney()).isEqualTo(8_500L);
    }

    @DisplayName("주차장 요금 무료(요금표가 변경되었습니다.) 일 최대 금액")
    @Test
    void parkingLotPayTest_MaximumDailyMoney() {
        ParkingLot parkingLot = new ParkingLot();
        EnterParkingLot enterParkingLot = new EnterParkingLot();
        ExitParkingLot exitParkingLot = new ExitParkingLot();
        User user = new User(1_5000L);
        Car car = new Car("1111", user);

        enterParkingLot.scan(parkingLot.getRepository(), car);
        exitParkingLot.exit(parkingLot.getRepository(), car, 360);
        assertThat(car.getUserMoney()).isZero();
    }

    @DisplayName("주차장 요금 무료(요금표가 변경되었습니다.) 일 최대 금액(경차요금할인)")
    @Test
    void parkingLotPayTest_Discount() {
        ParkingLot parkingLot = new ParkingLot();
        EnterParkingLot enterParkingLot = new EnterParkingLot();
        ExitParkingLot exitParkingLot = new ExitParkingLot();
        User user = new User(1_5000L);
        Car car = new Car("1111", user, 경차);

        enterParkingLot.scan(parkingLot.getRepository(), car);
        exitParkingLot.exit(parkingLot.getRepository(), car, 360);
        assertThat(car.getUserMoney()).isEqualTo(7_500L);
    }

    @DisplayName("사용자(User)가 Payco 회원인 경우에는 주차 요금이 10% 할인됩니다.(아닐경우 적용 X)")
    @Test
    void paycoMemberShipDiscountTest() {
        User user1 = new User(PAYCO, 1_0000L);
        User user2 = new User(1_0000L);
        Car car1 = new Car("1111", user1);
        Car car2 = new Car("2222", user2);
        ExitParkingLot exitParkingLot = new ExitParkingLot();
        ParkingLot parkingLot = new ParkingLot();
        EnterParkingLot enterParkingLot = new EnterParkingLot();

        enterParkingLot.scan(parkingLot.getRepository(), car1);
        enterParkingLot.scan(parkingLot.getRepository(), car2);

        exitParkingLot.exit(parkingLot.getRepository(), car1, 31);
        exitParkingLot.exit(parkingLot.getRepository(), car2, 31);

        assertThat(user1.getMoney()).isEqualTo(9100L);
        assertThat(user2.getMoney()).isEqualTo(9000L);
    }

    @DisplayName("사용자(User)가 Payco 회원인 경우에는 주차 요금이 10% 할인됩니다.(경차할인 중복적용확인)")
    @Test
    void paycoMemberShipDiscount_CarTypeTest() {
        User user1 = new User(PAYCO, 1_0000L);
        User user2 = new User(1_0000L);
        Car car1 = new Car("1111", user1, 경차);
        Car car2 = new Car("2222", user2, 경차);

        ExitParkingLot exitParkingLot = new ExitParkingLot();
        ParkingLot parkingLot = new ParkingLot();
        EnterParkingLot enterParkingLot = new EnterParkingLot();

        enterParkingLot.scan(parkingLot.getRepository(), car1);
        enterParkingLot.scan(parkingLot.getRepository(), car2);

        exitParkingLot.exit(parkingLot.getRepository(), car1, 31);
        exitParkingLot.exit(parkingLot.getRepository(), car2, 31);

        assertThat(user1.getMoney()).isEqualTo(9550L);
        assertThat(user2.getMoney()).isEqualTo(9500L);
    }

}
