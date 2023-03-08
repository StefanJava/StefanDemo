package com.stefan.spi;

import java.util.List;

/**
 * @description: 数据库搜索
 * @author: stefanyang
 * @date: 2023/3/8 20:39
 * @version: 1.0
 */
public class DatabaseSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("数据库搜索");
        return null;
    }
}
