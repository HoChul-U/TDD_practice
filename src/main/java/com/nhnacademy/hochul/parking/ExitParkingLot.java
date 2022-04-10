package com.nhnacademy.hochul.parking;

import static com.nhnacademy.hochul.car.CarType.경차;

import com.nhnacademy.hochul.car.Car;
import com.nhnacademy.hochul.policy.ParkingLotFeeVersion2;
import com.nhnacademy.hochul.policy.ParkingLotPolicy;
import com.nhnacademy.hochul.server.PaycoServer;
import com.nhnacademy.hochul.user.User;
import java.util.Map;

public class ExitParkingLot {
    private PaycoServer paycoServer = new PaycoServer();
    private ParkingLotPolicy policy = new ParkingLotFeeVersion2();

    public void exit(Map<String, Car> map, Car car, int time){
        time -= checkCoupon(car.getUser().getCoupon());
        policy.payFee(car,time);
        map.remove(car.getNumber());
    }

    public boolean barcode(User user) {
        return paycoServer.checkMember(user);
    }

    public int checkCoupon(int coupon) {
        return coupon * 60;
    }
}
