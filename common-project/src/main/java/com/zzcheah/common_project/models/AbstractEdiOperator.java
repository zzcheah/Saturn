package com.zzcheah.common_project.models;

import com.zzcheah.common_project.enums.IncomingOperations;
import lombok.Getter;

@Getter
public abstract class AbstractEdiOperator {

    protected final String ediType;
    protected final IncomingOperations operation;
    protected AbstractEdiOperator(String ediType, IncomingOperations operation) {
        this.ediType = ediType;
        this.operation = operation;
    }

    public abstract void operate(EdiPipeline pipeline);

}
