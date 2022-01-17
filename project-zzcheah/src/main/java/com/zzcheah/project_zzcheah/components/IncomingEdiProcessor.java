package com.zzcheah.project_zzcheah.components;

import com.zzcheah.common_project.interfaces.IIncomingEdiProcessor;
import com.zzcheah.common_project.models.EdiPipeline;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@AllArgsConstructor
public class IncomingEdiProcessor implements IIncomingEdiProcessor {

    @Override
    public void configure(EdiPipeline pipeline) {

    }

    @Override
    public void validate(EdiPipeline pipeline) {

    }

    @Override
    public void map(EdiPipeline pipeline) {

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

    @Override
    public void upload(EdiPipeline pipeline) {

    }

    @Override
    public void notify(EdiPipeline pipeline) {

    }
}
