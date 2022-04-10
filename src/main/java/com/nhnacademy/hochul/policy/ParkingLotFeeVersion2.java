package com.nhnacademy.hochul.policy;

import static com.nhnacademy.hochul.car.CarType.경차;

import com.nhnacademy.hochul.car.Car;
import com.nhnacademy.hochul.server.PaycoServer;
import com.nhnacademy.hochul.user.User;

public class ParkingLotFeeVersion2 implements ParkingLotPolicy{
    private static final int MAX_DAY_MONEY = 15000;
    private static final int FIRST_30MIN = 0;
    private static final int ADD_10MIN = 500;
    private static final int BETWEEN_30_AND_60MIN =1000;

    private final PaycoServer paycoServer = new PaycoServer();

    @Override
    public void payFee(Car car, int time) {
        int discount = 0;
        if (car.getCarType() != 경차) {
            if (barcode(car.getUser())) discount = feeRule(time) / 10;
            car.fee(car.getUserMoney() - (feeRule(time) - discount));
        }
        if (car.getCarType() == 경차) {
            if (barcode(car.getUser())) discount = (feeRule(time) / 2) / 10;
            car.fee(car.getUserMoney() - ((feeRule(time) / 2) - discount));
        }

    }
    private int feeRule(int time) {
        int fee = 0;
        int count = 0;
        if(time<=30) return FIRST_30MIN;
        if(time >=1440){
            count = time/1440;
            if(time%1440>=1) count++;
            return count * MAX_DAY_MONEY;
        }
        if(time <= 60){
            fee+=BETWEEN_30_AND_60MIN;
        }
        if(time>60){
            time-=60;
            count = time /10;
            if(time %10>=1) count++;
            fee+=BETWEEN_30_AND_60MIN+count*ADD_10MIN;
        }
        if(fee >=MAX_DAY_MONEY) fee = MAX_DAY_MONEY;
        return fee;
    }

    private boolean barcode(User user) {
        return paycoServer.checkMember(user);
    }
}
