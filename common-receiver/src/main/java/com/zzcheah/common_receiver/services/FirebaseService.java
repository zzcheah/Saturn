package com.zzcheah.common_receiver.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.io.ByteStreams;
import com.zzcheah.common_receiver.configurations.FirebaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;

@Service
@EnableAsync
public class FirebaseService {

    private final Storage storage;
    private final String bucketName;

    public FirebaseService(FirebaseConfig firebaseConfig,
                           @Value("${firebase.bucket}") String bucketName) throws IOException {

        this.bucketName = bucketName;

        ObjectMapper mapper = new ObjectMapper();
        String credString = mapper.writeValueAsString(firebaseConfig);
        InputStream credStream = new ByteArrayInputStream(credString.getBytes());

        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setProjectId(firebaseConfig.getProject_id())
                .setCredentials(GoogleCredentials.fromStream(credStream))
                .build();

        this.storage = storageOptions.getService();
    }

    @Async
    public void uploadFile(String fileName, InputStream inputStream) {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType("application/octet-stream")
                .setContentDisposition(String.format("attachment; filename=\"%s\"", fileName))
                .build();
        try (WriteChannel writer = storage.writer(blobInfo)) {
            ByteStreams.copy(inputStream, Channels.newOutputStream(writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finish uploading!");
    }
}
