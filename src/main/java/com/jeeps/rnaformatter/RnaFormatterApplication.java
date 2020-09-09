package com.jeeps.rnaformatter;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RnaFormatterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RnaFormatterApplication.class, args);
    }

}
