package com.zzcheah.system_mock1.components;

import com.zzcheah.common_project.enums.IncomingOperations;
import com.zzcheah.common_project.models.AbstractEdiOperator;
import com.zzcheah.common_project.models.AbstractIncomingEdiProcessor;
import com.zzcheah.common_project.models.EdiPipeline;
import org.springframework.stereotype.Service;

import java.util.Map;

public class System1EdiProcessor extends AbstractIncomingEdiProcessor {

    @Override
    public void runIncomingFlow(EdiPipeline pipeline) {
        String ediType = pipeline.getEdiType();
        Map<IncomingOperations, AbstractEdiOperator> operators = operatorMap.get(ediType);

        AbstractEdiOperator mapOperator = operators.get(IncomingOperations.MAP);
        mapOperator.operate(pipeline);

    }
}
