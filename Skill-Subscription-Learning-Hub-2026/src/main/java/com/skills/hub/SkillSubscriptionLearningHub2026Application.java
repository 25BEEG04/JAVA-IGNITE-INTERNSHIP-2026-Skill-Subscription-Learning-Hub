
package com.skills.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.skills.hub.repository")
@EntityScan(basePackages = "com.skills.hub.model")

public class SkillSubscriptionLearningHub2026Application {

	public static void main(String[] args) {
		SpringApplication.run(SkillSubscriptionLearningHub2026Application.class, args);
		System.out.println("Springboot has been successfully launched!");
	}

}
