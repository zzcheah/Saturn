package com.zzcheah.common_receiver.core;

public interface IReceiverProcessor {

    String backupReceivedFile();

    String publishToKafka();

    String syncProcess();

}
