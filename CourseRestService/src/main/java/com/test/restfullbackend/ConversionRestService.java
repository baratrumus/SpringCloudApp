package com.test.restfullbackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

@EnableEurekaClient
@SpringBootApplication
public class ConversionRestService {

    public static void main(String[] args) {
        SpringApplication.run(ConversionRestService.class, args);
    }

}
