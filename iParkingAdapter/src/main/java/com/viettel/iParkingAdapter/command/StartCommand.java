package com.viettel.iParkingAdapter.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.ws.rs.ApplicationPath;
import java.util.Collections;

@SpringBootApplication
@ApplicationPath("/command")
public class StartCommand {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(StartCommand.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "9103"));
        app.run(args);
    }

}
