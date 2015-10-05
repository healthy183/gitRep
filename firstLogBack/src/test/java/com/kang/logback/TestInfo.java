package com.kang.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestInfo {

    @Test
    public void runInfo(){

        //org.springframework.boot.logging.logback.LevelRemappingAppender
        
        log.debug("this is debug!");
        log.info("this is info!");
        log.warn("this is warn!");
        log.error("this is error!");
        System.out.println("print:i am Healthy!");
    }



}
