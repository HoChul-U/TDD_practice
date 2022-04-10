package com.nhnacademy.hochul.car;

import static com.nhnacademy.hochul.car.CarType.승용차;

import com.nhnacademy.hochul.exception.NegativeException;
import com.nhnacademy.hochul.exception.NotEnoughMoney;
import com.nhnacademy.hochul.user.User;

public class Car {
    private final String carNumber;
    private final User user;
    private String parkingNumber ="";
    private CarType carType;

    public Car(String carNumber,User user) {
        this.carNumber = carNumber;
        this.parkingNumber = "";
        this.carType= 승용차;
        this.user = user;
    }

    public Car(String carNumber,User user,CarType carType){
        this(carNumber,user);
        this.carType = carType;
    }

    public void fee(long money) {
        if(money<0)throw new NotEnoughMoney(money);
        this.user.setMoney(money);
    }

    public void setParkingNumber(String parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public String getNumber() {
        return this.carNumber;
    }

    public long getUserMoney() {
        if(this.user.getMoney() <0) throw new NegativeException(this.user.getMoney());
        return this.user.getMoney();
    }

    public String getParkingNumber() {
        return parkingNumber;
    }

    public User getUser() {
        return user;
    }

    public CarType getCarType() {
        return this.carType;
    }
}
