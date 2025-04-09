package com.stefan.ref;

import lombok.Data;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @description <功能描述>
 * @author: StefanYang
 * @Date: 2025/4/9 13:32
 */
public class PhantomRef {

    @Data
    public static class Tiger {
        private String name;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Tiger is dead");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReferenceQueue referenceQueue = new ReferenceQueue();

        List<byte[]> bytes = new ArrayList<>();

        PhantomReference<Tiger> phantomReference = new PhantomReference<>(new Tiger(), referenceQueue);

        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                bytes.add(new byte[1024 * 1024]);
            }
        }).start();

        new Thread(() -> {

            while (true) {
                Reference poll = referenceQueue.poll();
                if (poll != null) {
                    System.out.println("虚引用被回收了: " + poll);
                }
            }
        }).start();

        Thread.sleep(Long.MAX_VALUE);
    }
}
