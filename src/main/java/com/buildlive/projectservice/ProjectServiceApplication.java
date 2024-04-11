package com.buildlive.projectservice;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class ProjectServiceApplication {


	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		FileInputStream serviceAccount =
				new FileInputStream("D:\\BuildLive\\BuildLive-back\\project-service\\src\\main\\resources\\notification-a8cf1-firebase-adminsdk-945ae-d6775e7bfc.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();
		if(FirebaseApp.getApps().isEmpty()){
			FirebaseApp.initializeApp(options);
		}
		return FirebaseMessaging.getInstance();

//		GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
//				new ClassPathResource("notification-a8cf1-firebase-adminsdk-945ae-d6775e7bfc.json").getInputStream());
//		FirebaseOptions firebaseOptions = FirebaseOptions.builder()
//				.setCredentials(googleCredentials).build();
//		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions,"my-app");
//		return FirebaseMessaging.getInstance(app);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceApplication.class, args);
	}

}
