# Welcome to Microservice Spring boot Utils Tools!
## La cabecera del proyecto es:

```text
<groupId>com.luigivismara</groupId>
<artifactId>microservice-spring-boot-parent</artifactId>
<version>2.0</version>
<name>MicroService SpringBoot Parent</name>
<description>Microservice Lib with springboot, swagger, jwt, pageable</description>
<packaging>pom</packaging>
```


## Importar al proyecto
Para importar el parent al proyecto debemos escribir en la terminal  `mvn clean install` en la carpeta del proyecto luego de esto lo importamos a nuestro proyecto

```xml
<dependencies>
    <dependency>
        <groupId>com.luigivismara</groupId>
        <artifactId>microservice-utils-tools</artifactId>
        <version>2.0</version>
    </dependency>
</dependencies>
```

## Usar en nuestro proyecto
Para hacer uso de nuestra libreria en nuestro proyecto hacemos lo siguiente

`private PageableToolsImpl pageableToolsImpl = new PageableToolsImpl();`
    
En le futuro se va agregar el @Autowired para que se mire asi:

```java
@Autowired
private PageableToolsImpl pageableToolsImpl;
```
	
El pom deberia quedarnos con una estructura similar a la siguiente
```xml
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
		<java.version>21</java.version>
	</properties>

	<parent>
		<groupId>com.luigivismara</groupId>
		<artifactId>microservice-spring-boot-parent</artifactId>
		<version>2.0</version>
	</parent>
</project>
```