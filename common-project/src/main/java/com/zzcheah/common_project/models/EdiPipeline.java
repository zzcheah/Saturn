package com.zzcheah.common_project.models;

import lombok.*;

import java.io.InputStream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EdiPipeline {

    // core
    private String client;
    private String ediType;
    private String traceId;
    private String filename;
    private InputStream dataStream;
    private String fileKey;

    // fields
    private String errMsg;

    // status
    private boolean isDoneBackup;
    private boolean isPublishedKafka;

}
