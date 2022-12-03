# Welcome to Microservice Spring boot Libraries!

Hola esto es un parent tipo maven que sirve para importar librerias a los proyectos maven.

## La cabecera del proyecto es:
 

    <groupId>com.luigivismara</groupId>
	<artifactId>microservice-spring-boot-parent</artifactId>
	<version>1.3</version>
	<name>MicroService SpringBoot Parent</name>
	<description>Microservice Lib with springboot, swagger, jwt, pageable</description>
	<packaging>pom</packaging>

## Usamos las siguientes Librerias

    <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.6</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	
    <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>io.swagger.core.v3</groupId>
			<artifactId>swagger-annotations</artifactId>
			<version>2.2.2</version>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.11</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-core</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-crypto</artifactId>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
		</dependency>
	</dependencies>
		

## Importar al proyecto
Para importar el parent al proyecto debemos escribir en la terminal  `mvn clean install` en la carpeta del proyecto luego de esto lo importamos a nuestro proyecto

    <parent>
		<groupId>com.luigivismara</groupId>
		<artifactId>microservice-spring-boot-parent</artifactId>
		<version>1.0</version>
	</parent>
El pom deberia quedarnos con una estructura similar a la siguiente

    <?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">	
	
	<modelVersion>4.0.0</modelVersion>
	
	<groupId>com.example</groupId>
	<artifactId>monitoring</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>monitoring</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<microservice.port>8081</microservice.port>
		<java.version>17</java.version>
	</properties>

	<parent>
		<groupId>com.luigivismara</groupId>
		<artifactId>microservice-spring-boot-parent</artifactId>
		<version>1.0</version>
	</parent>

	<build>
		<plugins>
			<!-- Swagger auto configuration -->
			<plugin>
				<groupId>org.springdoc</groupId>
				<artifactId>springdoc-openapi-maven-plugin</artifactId>
				<version>0.2</version>
				<executions>
					<execution>
						<phase>integration-test</phase>
						<goals>
							<goal>generate</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<apiDocsUrl>http://localhost:${microservice.port}/v3/api-docs</apiDocsUrl>
					<outputFileName>openapi.json</outputFileName>
					<outputDir>${project.build.directory}</outputDir>
				</configuration>
			</plugin>

		</plugins>
	</build>

</project>

## Para configurar el swagger copiar lo siguiente y pegarlo en su custom project:
 

    <!-- Swagger auto configuration -->
			<plugin>
				<groupId>org.springdoc</groupId>
				<artifactId>springdoc-openapi-maven-plugin</artifactId>
				<version>0.2</version>
				<executions>
					<execution>
						<phase>integration-test</phase>
						<goals>
							<goal>generate</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<apiDocsUrl>http://localhost:${microservice.port}/v3/api-docs</apiDocsUrl>
					<outputFileName>openapi.json</outputFileName>
					<outputDir>${project.build.directory}</outputDir>
				</configuration>
			</plugin>
