package com.stefan.proxy.service.impl;

import com.stefan.proxy.service.SmsService;

public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message " + message);
        return message;
    }
}
