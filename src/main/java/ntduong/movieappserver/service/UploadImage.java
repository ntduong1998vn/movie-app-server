package ntduong.movieappserver.service;

import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class UploadImage {

    Bucket storage;

    @Autowired
    public UploadImage(Bucket storage) {
        this.storage = storage;
    }

    public void uploadImage( String projectId, String bucketName, String objectName, String filePath) throws IOException{
        // The ID of your GCP project
        // String projectId = "your-project-id";

        // The ID of your GCS bucket
        // String bucketName = "your-unique-bucket-name";

        // The ID of your GCS object
        // String objectName = "your-object-name";

        // The path to your file to upload
        // String filePath = "path/to/your/file"
//        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
//        BlobId blobId = BlobId.of(bucketName, objectName);
//        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        File file;
        InputStream image = new FileInputStream(filePath);

        storage.create(objectName, image,"image/jpeg");
        System.out.println(
                "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
    }
}
