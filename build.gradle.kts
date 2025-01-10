plugins {
	java
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.adhd"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations["annotationProcessor"])
	}
}


repositories {
	mavenCentral()
}

dependencies {
	/** Spring Boot Starters */
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc") // JDBC support
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA support
	implementation("org.springframework.boot:spring-boot-starter-data-redis") // Redis support
	implementation("org.springframework.boot:spring-boot-starter-security") // Spring Security
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf") // Thymeleaf templating engine
	implementation("org.springframework.boot:spring-boot-starter-web") // Spring MVC and REST support

	/** Thymeleaf Extras */
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6") // Thymeleaf + Spring Security integration

	/** Lombok for code generation */
	compileOnly("org.projectlombok:lombok") // Lombok compile-time annotations
	annotationProcessor("org.projectlombok:lombok") // Annotation processor for Lombok

	/** Javax Validation API */
	implementation("jakarta.validation:jakarta.validation-api") // Validation API
	implementation("org.hibernate.validator:hibernate-validator") // Hibernate Validator

	/** Developer Tools */
	developmentOnly("org.springframework.boot:spring-boot-devtools") // Auto-reload for development

	/** Database Drivers */
	runtimeOnly("com.mysql:mysql-connector-j") // MySQL driver

	/** Testing Dependencies */
	testImplementation("org.springframework.boot:spring-boot-starter-test") // Spring Boot testing support
	testImplementation("org.springframework.security:spring-security-test") // Spring Security test utilities
	testRuntimeOnly("org.junit.platform:junit-platform-launcher") // JUnit platform launcher for runtime testing
}

tasks.withType<Test> {
	useJUnitPlatform()
}