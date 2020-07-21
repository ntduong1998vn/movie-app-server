/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.util;

import com.google.cloud.storage.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Slf4j
@Component
public class ImageUtil {

    @Autowired
    Bucket bucket;

    public void uploadImage(String projectId, String bucketName, String fileName, String contentType, InputStream data) {
        bucket.create(bucketName + fileName, data, contentType);
        log.info("File " + fileName + " uploaded to bucket " + bucketName + " as " + fileName);
    }

    public void uploadImage(String bucketName, String fileName, String contentType, InputStream data) {
        bucket.create(bucketName + fileName, data, contentType);
        log.info("File " + fileName + " uploaded to bucket " + bucketName + " as " + fileName);
    }

    public boolean deleteImage(String bucketName,String fileName){
        Storage storage = bucket.getStorage();
        return storage.delete(bucket.getName(),bucketName+fileName);
    }
}
