package ntduong.movieappserver.service;

import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;


@Component
public class DownloadImage {

    Bucket storage;

    @Autowired
    public DownloadImage(Bucket storage){
        this.storage = storage;
    }

    public void downloadObject(
            String projectId, String bucketName, String objectName, Path destFilePath) {
        // The ID of your GCP project
        // String projectId = "your-project-id";

        // The ID of your GCS bucket
        // String bucketName = "your-unique-bucket-name";

        // The ID of your GCS object
        // String objectName = "your-object-name";

        // The path to which the file should be downloaded
        // Path destFilePath = Paths.get("/local/path/to/file.txt");

        Blob blob = storage.get(objectName);
        blob.downloadTo(destFilePath);
        System.out.println(
                "Downloaded object "
                        + objectName
                        + " from bucket name "
                        + bucketName
                        + " to "
                        + destFilePath);
    }

}
