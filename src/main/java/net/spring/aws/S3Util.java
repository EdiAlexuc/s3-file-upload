package net.spring.aws;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;

public class S3Util {

    private static final String BUCKET = "file-sharing-edi";

    public static void uploadFile(String filename, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {
        S3Client client = S3Client.builder().build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(filename)
                .build();


        client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
    }
}
