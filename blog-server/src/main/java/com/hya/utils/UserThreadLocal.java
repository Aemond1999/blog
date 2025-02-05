package com.hya.utils;

import com.hya.common.domain.UserDo;

public class UserThreadLocal {

    private UserThreadLocal(){}

    private static final ThreadLocal<UserDo> LOCAL = new ThreadLocal<>();

    public static void put(UserDo user){
        LOCAL.set(user);
    }
    public static UserDo get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
