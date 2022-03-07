package com.zzcheah.receiver_http;

import com.zzcheah.common_receiver.annotations.EnableEdiReceiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEdiReceiver
public class ReceiverHttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiverHttpApplication.class,args);

    }
}
