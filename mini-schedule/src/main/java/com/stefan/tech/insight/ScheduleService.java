package com.stefan.tech.insight;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @description 定时器
 * @author: StefanYang
 * @Date: 2025/4/7 18:25
 */
public class ScheduleService {

    // 线程池
    ExecutorService executorService = Executors.newFixedThreadPool(6);

    // 触发器
    Trigger trigger = new Trigger();

    public void schedule(Runnable runnable, long delay) {

        Job job = new Job();
        job.setTask(runnable);
        job.setStartTime(System.currentTimeMillis() + delay);
        job.setDelay(delay);
        trigger.queue.offer(job);
        // 有新任务时，唤醒线程
        trigger.wakeUp();
    }

    class Trigger {

        PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

        Thread thread = new Thread(() -> {

            while (true) {

                while (queue.isEmpty()) {
                    LockSupport.park();
                }

                Job latelyJob = queue.peek();
                if (System.currentTimeMillis() >= latelyJob.getStartTime()) {
                    latelyJob = queue.poll();
                    executorService.execute(latelyJob.getTask());

                    // 重复执行
                    Job job = new Job();
                    job.setTask(latelyJob.getTask());
                    job.setStartTime(System.currentTimeMillis() + latelyJob.getDelay());
                    job.setDelay(latelyJob.getDelay());
                    queue.offer(job);
                } else {
                    LockSupport.parkUntil(latelyJob.getStartTime());
                }

            }

        });

        {
            thread.start();
        }

        public void wakeUp() {
            LockSupport.unpark(thread);
        }

    }

}
