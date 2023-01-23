package com.example.addressbookapp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
@Slf4j
public class AddressBookAppApplication {
public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(AddressBookAppApplication.class, args);
        log.info("Address book app started in {} Environment", context.getEnvironment().getProperty("environment"));
        log.info("Address book DB User is {} Environment", context.getEnvironment().getProperty("spring.datasource.username"));
  }
}