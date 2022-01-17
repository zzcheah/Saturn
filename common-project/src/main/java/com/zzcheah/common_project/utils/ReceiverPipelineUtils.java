package com.zzcheah.common_project.utils;

import com.zzcheah.common_project.models.EdiPipeline;
import org.apache.commons.lang3.StringUtils;

public class ReceiverPipelineUtils {

    //todo: change this
    public static boolean isSuccessProcessing(EdiPipeline p) {
        if (!StringUtils.isEmpty(p.getErrMsg())) return false;
        return p.isDoneBackup() && p.isPublishedKafka();
    }
}
