package com.zzcheah.common_project.models;

import com.zzcheah.common_project.enums.IncomingOperations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractIncomingEdiProcessor {

    protected Map<String, Map<IncomingOperations, AbstractEdiOperator>> operatorMap;

    public AbstractIncomingEdiProcessor() {
        this.operatorMap = new HashMap<>();
    }

    public abstract void runIncomingFlow(EdiPipeline pipeline);

    @Autowired
    public final void setConfigureOperatorMap(List<AbstractEdiOperator> operators) {
        for (AbstractEdiOperator operator : operators) {
            String ediType = operator.getEdiType();
            if (!operatorMap.containsKey(ediType)) operatorMap.put(ediType, new HashMap<>());
            operatorMap.get(ediType).put(operator.getOperation(), operator);
        }
    }

}
