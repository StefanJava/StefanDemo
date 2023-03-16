package com.stefan.thread.demo;

/**
 * @description:
 * @author: stefanyang
 * @date: 2023/3/13 14:05
 * @version: 1.0
 */
public class Demo002 {
    public static void main(String[] args) {
        JoinExample joinExample = new JoinExample();
        joinExample.test();
    }
}

class JoinExample {

    private static class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    private static class B extends Thread {

        private A a;

        public B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    public void test() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }

}
