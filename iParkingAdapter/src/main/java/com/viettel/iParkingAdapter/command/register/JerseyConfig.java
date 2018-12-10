package com.viettel.iParkingAdapter.command.register;

import com.viettel.iParkingAdapter.command.CommandController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/command")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){

        register(CommandController.class);
    }
}
