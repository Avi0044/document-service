Following changes needs to be done:

#### In pom.xml include the following snippets

Replace the placeholders < > accordingly

```xml
<distributionManagement>
    <snapshotRepository>
        <id>snapshots</id>
        <name>paynearby-artifactory-primary-0-snapshots</name>
        <url>https://paynearby.jfrog.io/artifactory/<PROJECT_NAME>-libs-snapshot-local</url>
    </snapshotRepository>
    <repository>
        <id>central</id>
        <name>paynearby-artifactory-primary-0-releases</name>
        <url>https://paynearby.jfrog.io/artifactory/<PROJECT_NAME>-libs-release-local</url>
    </repository>
</distributionManagement>
```

```xml
<repositories>
    <repository>
        <id>central</id>
        <url>https://paynearby.jfrog.io/artifactory/<PROJECT_NAME>-libs-release</url>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <name><PROJECT_NAME>-libs-release</name>
    </repository>
    <repository>
        <id>snapshots</id>
        <url>https://paynearby.jfrog.io/artifactory/<PROJECT_NAME>-libs-snapshot</url>
        <releases>
            <enabled>false</enabled>
        </releases>
        <name><PROJECT_NAME>-libs-snapshot</name>
    </repository>
</repositories>
<pluginRepositories>
    <pluginRepository>
        <id>central</id>
        <url>https://paynearby.jfrog.io/artifactory/<PROJECT_NAME>-libs-release</url>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <name><PROJECT_NAME>-libs-release</name>
    </pluginRepository>
    <pluginRepository>
        <id>snapshots</id>
        <url>https://paynearby.jfrog.io/artifactory/<PROJECT_NAME>-libs-snapshot</url>
        <releases>
            <enabled>false</enabled>
        </releases>
        <name><PROJECT_NAME>-libs-snapshot</name>
    </pluginRepository>
</pluginRepositories>
```

#### In the settings.xml add the following snippet

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 http://maven.apache.org/xsd/settings-1.2.0.xsd" xmlns="http://maven.apache.org/SETTINGS/1.2.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <servers>
        <server>
            <username><USERNAME></username>
            <password><PASSWORD></password>
            <id>central</id>
        </server>
        <server>
            <username><USERNAME></username>
            <password><PASSWORD></password>
            <id>snapshots</id>
        </server>
    </servers>
</settings>
```
