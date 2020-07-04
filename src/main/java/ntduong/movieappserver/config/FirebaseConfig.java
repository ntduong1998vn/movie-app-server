package ntduong.movieappserver.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public Bucket firebaseStorage(){
        return StorageClient.getInstance().bucket();
    }

    @Value("${app.firebase-configuration-file}")
    private String configPath;

    @Value("${app.storageUrl}")
    private String storageUrl;

    @PostConstruct
    public void init() throws IOException {
        InputStream inputStream = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setStorageBucket(storageUrl)
                .build();

        FirebaseApp.initializeApp(options);
    }
}
