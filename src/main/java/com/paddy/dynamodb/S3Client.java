package com.paddy.dynamodb;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.json.Jackson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class S3Client {

    static String BUCKET = "birdus";

    public void upload(ArrayList models) {

        String todaysDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        String format = String.format("%s.json", todaysDate);
        File file = new File(format);


        try {
            AmazonS3 s3Client;
            s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.EU_WEST_1)
                    .withCredentials(new ProfileCredentialsProvider())
                    .build();

            Jackson.getWriter().writeValue(file, models);

            PutObjectRequest request = new PutObjectRequest(BUCKET, file.getName(), file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("application/json");
            metadata.addUserMetadata("x-amz-meta-title", "birdus");
            request.setMetadata(metadata);
            PutObjectResult putObjectResult = s3Client.putObject(request);
            System.out.println("Uploaded to s3 : " + putObjectResult.getETag());
        } catch (SdkClientException | IOException e) {
            e.printStackTrace();
        }
    }
}
