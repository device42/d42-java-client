### Add repository

```xml
<repositories>
    <repository>
        <id>d42-java-client-mvn-repo</id>
        <url>https://github.com/device42/d42-java-client/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```
### Add dependency to pom.xml

```xml
<dependency>
    <groupId>com.device42</groupId>
     <artifactId>d42-java-client</artifactId>
     <version>1.0.13</version>
</dependency>
```
### Example for ApplicationComponent

```java
ApplicationComponentsRestClient appCompsRestClient=Device42ClientFactory.createApplicationComponentsRestClient("https://example.device42.com","username","password");
                                                   
ApplicationComponentParameters.ApplicationComponentParametersBuilder appCompsParametersBuilder=new ApplicationComponentParameters.ApplicationComponentParametersBuilder();

ApplicationComponentParameters appCompsParameters=appCompsParametersBuilder.deviceId(2018l).build();
List<ApplicationComponent> list=appCompsRestClient.getApplicationComponents(appCompsParameters);

ApplicationComponent appComponent=new ApplicationComponent("app-name", "device-name");
appComponent.setJsonConfig("{\"config\":\"parameters\"}");

appCompsRestClient.createOrUpdateApplicationComponent(appComponent);
```
