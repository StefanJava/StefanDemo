package com.stefan.ref;

import java.lang.ref.SoftReference;

/**
 * @description <功能描述>
 * @author: StefanYang
 * @Date: 2025/4/9 13:17
 */
public class SoftRef {
    public static void main(String[] args) {

        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());
        byte[] bytes = new byte[1024 * 1024 * 10];
        System.out.println(softReference.get());
    }
}
