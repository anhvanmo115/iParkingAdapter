package com.viettel.iParkingAdapter.config;

import com.viettel.iParkingAdapter.server.TCPServer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Created by luyenct on 3/15/2018.
 */
@Component
public class StartupBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${adapter.port}")
    private Integer PORT;

    private TCPServer tcpServer;

    private static Logger logger = LogManager.getLogger(StartupBootstrap.class);

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        Thread thread = new Thread(()->{
            try {
                tcpServer = new TCPServer(PORT);
                tcpServer.startServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();

        return;
    }

    @PreDestroy
    public void onDestroy(){
        logger.info("shutdown server ...");
        tcpServer.stopServer();
    }


}
