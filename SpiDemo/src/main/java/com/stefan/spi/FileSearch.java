package com.stefan.spi;

import java.util.List;

/**
 * @description: 文件搜索
 * @author: stefanyang
 * @date: 2023/3/8 20:38
 * @version: 1.0
 */
public class FileSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索");
        return null;
    }
}
