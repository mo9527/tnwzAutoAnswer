package com.wtwei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wtwei .
 * @date 2018/1/17 .
 * @time 15:31 .
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ScanFileChangeTask task = new ScanFileChangeTask();
        new Thread(task).start();
    }
}
