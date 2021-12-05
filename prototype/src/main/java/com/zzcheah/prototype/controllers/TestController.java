package com.zzcheah.prototype.controllers;

import com.zzcheah.common_base.CommonBaseClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {


    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    public TestController() {
        log.info("started test controller");
    }

    @GetMapping("log")
    public CommonBaseClass logBaseClass(){

        log.info("logging");
        CommonBaseClass ha = new CommonBaseClass();
        ha.setCommonField1("abc");
        ha.setCommonField2("efg");
        ha.setCommonField3("haha");
        System.out.println("do something from rest trigger");
        ha.doSomething();
        streamBridge.send("testTopic", ha);
        return ha;
    }


}
