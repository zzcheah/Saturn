package com.zzcheah.common_base;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Slf4j
public class CommonBaseClass {

    private String commonField1;
    private String commonField2;
    private String commonField3;

    public void doSomething() {
        log.info("HELLO!");
        log.info(commonField2);
    }

}
