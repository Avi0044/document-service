# Document-Service Project

Document Generator Service allows you to easily generate documents i.e pdf, zip.
It reduces the development and support costs by enabling your users to create and manage their document templates.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw clean package
```

It produces the `document-service-*.jar` file in the `target/` directory.

If you want to package it without running unit test cases, execute the following command:

```shell script
./mvnw clean package -Dmaven.test.skip=true
```

The application is now runnable using `java -jar /target/document-service-*.jar`.