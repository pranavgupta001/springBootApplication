package com.TruckBooking.Security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class FirebaseConfig {

	@Value("${firebase.credentials.file}")
	private String credentialsFile;

	@Value("${firebase.app.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(FirebaseConfig.class, args);
	}

	@PostConstruct
	private void initializeFirebaseApp() {
		try (InputStream serviceAccount = FirebaseConfig.class.getClassLoader().getResourceAsStream(credentialsFile)) {
			assert serviceAccount != null;
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			FirebaseApp.initializeApp(options, appName); // Provide a unique app name
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


