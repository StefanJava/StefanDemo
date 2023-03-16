package com.stefan.io.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @description: url
 * @author: stefanyang
 * @date: 2023/3/16 20:35
 * @version: 1.0
 */
public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.youtube.com/");
        try (InputStream is = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        }
    }
}
