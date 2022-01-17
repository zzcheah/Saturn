package com.zzcheah.common_project.interfaces;

import com.zzcheah.common_project.models.EdiPipeline;

public interface IIncomingEdiProcessor {

    void configure(EdiPipeline pipeline);
    void validate(EdiPipeline pipeline);
    void map(EdiPipeline pipeline);
    void upload(EdiPipeline pipeline);
    void notify(EdiPipeline pipeline);

}
