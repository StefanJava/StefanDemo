package com.stefan.proxy.staticproxy;

import com.stefan.proxy.service.SmsService;

public class SmsProxy implements SmsService {

    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        System.out.println("before method send()");
        smsService.send(message);
        System.out.println("after method send()");
        return message;
    }
}
