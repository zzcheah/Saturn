package com.zzcheah.project_zzcheah.incoming.sku;

import com.zzcheah.common_project.enums.IncomingOperations;
import com.zzcheah.common_project.models.AbstractEdiOperator;
import com.zzcheah.common_project.models.EdiPipeline;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component("SKU-Map")
public class UploadOperator extends AbstractEdiOperator{

    protected UploadOperator() {
        super("SKU", IncomingOperations.MAP);
    }

    public void operate(EdiPipeline pipeline) {

        System.out.println("MAPPING");
        try {
            byte[] incomingBytes = IOUtils.toByteArray(pipeline.getDataStream());
            String incomingContent = new String(incomingBytes, StandardCharsets.UTF_8);
            System.out.println(incomingContent);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("DONE Mapping");

    }

}
