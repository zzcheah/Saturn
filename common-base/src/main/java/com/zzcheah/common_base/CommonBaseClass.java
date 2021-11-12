package com.zzcheah.common_base;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommonBaseClass {

    private String commonField1;
    private String commonField2;
    private String commonField3;

    public void doSomething() {
        System.out.println("HELLO!");
        System.out.println(commonField2);
    }

}
