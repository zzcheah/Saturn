package com.zzcheah.common_receiver.utils;

import com.zzcheah.common_receiver.models.ReceiverPipeline;
import org.apache.commons.lang3.StringUtils;

public class ReceiverPipelineUtils {

    public static boolean isSuccessProcessing(ReceiverPipeline p) {
        if (!StringUtils.isEmpty(p.getErrMsg())) return false;
        return p.isDoneBackup() && p.isPublishedKafka();
    }
}
