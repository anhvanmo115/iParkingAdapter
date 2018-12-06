package com.viettel.iParkingAdapter.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.ws.rs.ApplicationPath;

@SpringBootApplication
@ApplicationPath("/command")
public class StartCommand {
    public static void main(String[] args) {

        SpringApplication.run(StartCommand.class, args);

    }

}
