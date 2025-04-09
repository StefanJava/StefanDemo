package com.stefan.ref;

import java.lang.ref.WeakReference;

/**
 * @description <功能描述>
 * @author: StefanYang
 * @Date: 2025/4/9 13:23
 */
public class WeakRef {
    public static void main(String[] args) {
        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1]);
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
}
