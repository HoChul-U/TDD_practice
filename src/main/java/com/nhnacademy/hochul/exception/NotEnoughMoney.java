package com.nhnacademy.hochul.exception;

public class NotEnoughMoney extends IllegalArgumentException {
    public NotEnoughMoney(long money){
        super("You can't pay fee. You don't have Money. money : " + money);
    }
}
