package com.carndos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = "com.carndos.modules", annotationClass = org.apache.ibatis.annotations.Mapper.class)
public class CarnDosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarnDosApplication.class, args);
    }

}
