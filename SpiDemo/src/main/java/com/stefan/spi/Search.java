package com.stefan.spi;

import java.util.List;

/**
 * @description: 搜索
 * @author: stefanyang
 * @date: 2023/3/8 20:37
 * @version: 1.0
 */
public interface Search {

    /**
     *
     * @param keyword keyword
     * @return list
     */
    List<String> searchDoc(String keyword);
}
