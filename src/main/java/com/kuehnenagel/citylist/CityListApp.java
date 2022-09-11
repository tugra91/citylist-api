package com.kuehnenagel.citylist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration()
@ComponentScan(basePackages = {"com.kuehnenagel.citylist"})
public class CityListApp {

    public static void main(String[] args) {
        SpringApplication.run(CityListApp.class, args);
    }
}
