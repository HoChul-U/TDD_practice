package com.nhnacademy.hochul.server;

import static com.nhnacademy.hochul.user.UserType.PAYCO;

import com.nhnacademy.hochul.user.User;

public class PaycoServer {
    public boolean checkMember(User user){
        return user.getUserType() == PAYCO;
    }
}
