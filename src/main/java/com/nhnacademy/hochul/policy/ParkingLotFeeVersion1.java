package com.nhnacademy.hochul.policy;

import com.nhnacademy.hochul.car.Car;

public class ParkingLotFeeVersion1 implements ParkingLotPolicy{
    private static final int MAX_DAY_MONEY = 10000;
    private static final int FIRST_30MIN = 1000;
    private static final int ADD_10MIN = 10000;

    @Override
    public void payFee(Car car, int time) {
        int count = 0;
        if(time>=360){
            car.fee(car.getUserMoney() -MAX_DAY_MONEY);
            if(time/1440 >=1){
                count = time/1440;
                car.fee(car.getUserMoney()- (long) MAX_DAY_MONEY *count);
            }
        }
        else{
            if (time >= 30) {
                car.fee(car.getUserMoney() - FIRST_30MIN);
                time -= 30;
            }
            if (time >= 1) {
                if(time%10>=1) count++;
                count += time/10;
                car.fee(car.getUserMoney() - count * ADD_10MIN);
            }
        }
    }
}
