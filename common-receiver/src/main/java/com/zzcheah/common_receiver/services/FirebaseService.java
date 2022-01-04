package com.zzcheah.common_receiver.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.zzcheah.common_receiver.configurations.FirebaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;

@Service
//@EnableAsync
@Slf4j
public class FirebaseService {

    private final Storage storage;
    private final String bucketName;
    private final FirebaseConfig firebaseConfig;

    public FirebaseService(FirebaseConfig firebaseConfig,
                           @Value("${firebase.bucket}") String bucketName) throws IOException {

        this.bucketName = bucketName;
        this.firebaseConfig = firebaseConfig;

        ObjectMapper mapper = new ObjectMapper();
        String credString = mapper.writeValueAsString(firebaseConfig);
        InputStream credStream = new ByteArrayInputStream(credString.getBytes());

        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setProjectId(firebaseConfig.getProject_id())
                .setCredentials(GoogleCredentials.fromStream(credStream))
                .build();

        this.storage = storageOptions.getService();
    }

//    @Async
    public void uploadFile(String fileName, InputStream inputStream) {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType("text/plain")
                .setContentDisposition(String.format("attachment; filename=\"%s\"", fileName))
                .build();
        try (WriteChannel writer = storage.writer(blobInfo)) {
            IOUtils.copy(inputStream, Channels.newOutputStream(writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finish uploading!");
    }
}
