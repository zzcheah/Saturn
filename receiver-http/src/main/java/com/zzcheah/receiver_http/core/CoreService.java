package com.zzcheah.receiver_http.core;

import com.zzcheah.common_receiver.services.FirebaseService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@ComponentScan({
        "com.zzcheah.common_receiver.services",
        "com.zzcheah.common_receiver.configurations"
})
@AllArgsConstructor
public class CoreService {

    private final FirebaseService firebaseService;

    @PostConstruct
    private void init() {
//        generateHelloWorldTxt();
    }

    private void generateHelloWorldTxt() {
        String data = "Hello from spring boot! Should be Async";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        firebaseService.uploadFile("AsyncHelloWorld.txt",inputStream);
    }


}
