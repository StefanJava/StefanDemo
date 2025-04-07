package com.stefan.tech.insight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description <功能描述>
 * @author: StefanYang
 * @Date: 2025/4/7 18:33
 */
public class Main {
    public static void main(String[] args) {
        ScheduleService scheduleService = new ScheduleService();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");

        System.out.println("每隔100毫秒执行一次的任务开始了");
        scheduleService.schedule(() -> {
            System.out.println(dateTimeFormatter.format(LocalDateTime.now()) + "每隔100毫秒执行一次");
        }, 100);

        System.out.println("每隔200毫秒执行一次的任务开始了");
        scheduleService.schedule(() -> {
            System.out.println(dateTimeFormatter.format(LocalDateTime.now()) + "每隔200毫秒执行一次");
        }, 200);
    }
}
