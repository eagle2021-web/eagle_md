package com.eagle.mysql.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableScheduling
@Slf4j
public class MyScheduledTasks {

    @Scheduled(cron = "0/3 * * * * ?") // 每分钟执行一次
    public void myTask() {
        // 定时任务逻辑
        System.out.println("定时任务执行了！");
        log.error("dasfssdafsdf");
    }

}