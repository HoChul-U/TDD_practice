package com.nhnacademy.hochul.exception;

public class NegativeException extends IllegalArgumentException {
    public NegativeException(long money){
        super("Money can't be Negative. money : " + money);
    }
}
