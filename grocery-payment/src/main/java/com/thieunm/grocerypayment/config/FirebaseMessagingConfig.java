package com.thieunm.grocerypayment.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Objects;

@Configuration
public class FirebaseMessagingConfig {

    @Value("${firebase.admin.json-path}")
    private String firebaseJsonPath;

    @Value("${firebase.admin.app-name}")
    private String firebaseAppName;

    @Bean
    @SneakyThrows
    public FirebaseMessaging firebaseMessaging() {
        try (InputStream firebaseJsonPathInputStream = FirebaseMessagingConfig.class.getResourceAsStream(firebaseJsonPath)) {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(Objects.requireNonNull(firebaseJsonPathInputStream));
            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(googleCredentials)
                    .build();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions, firebaseAppName);
            return FirebaseMessaging.getInstance(firebaseApp);
        }
    }
}
