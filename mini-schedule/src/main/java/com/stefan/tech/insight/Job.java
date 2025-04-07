package com.stefan.tech.insight;

import java.util.Comparator;

/**
 * @description <功能描述>
 * @author: StefanYang
 * @Date: 2025/4/7 19:09
 */
public class Job implements Comparable<Job> {

    private Runnable task;

    private Long startTime;

    private Long delay;

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    @Override
    public int compareTo(Job o) {
        return (int) (this.getStartTime() - o.getStartTime());
    }
}
