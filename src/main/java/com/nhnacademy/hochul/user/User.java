package com.nhnacademy.hochul.user;

import static com.nhnacademy.hochul.user.UserType.NORMAL;

public class User {
    private final UserType userType;
    private final int coupon;
    private long money;


    public User(UserType userType, long money) {
        this(userType,money,0);
    }
    public User(UserType userType, long money , int coupon){
        this.userType = userType;
        this.money =money;
        this.coupon = coupon;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public User(long money) {
        this(NORMAL,money);
    }

    public UserType getUserType() {
        return userType;
    }

    public int getCoupon() {
        return coupon;
    }

    public long getMoney() {
        return this.money;
    }
}
