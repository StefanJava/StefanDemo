package com.stefan.ref;

import lombok.Data;

/**
 * @description <功能描述>
 * @author: StefanYang
 * @Date: 2025/4/9 13:14
 */
public class Ref {

    @Data
    public static class Tiger {

        private String name;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Tiger is dead");
        }
    }

    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        tiger = null;
        System.gc();
    }

}
