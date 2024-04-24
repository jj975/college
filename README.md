# College

## **Installation Dependencies**
1. Documentation GWT: [GWT Project](https://www.gwtproject.org/overview.html)
2. Dependency: Java SE Development Kit (JDK) [Adoptium](https://adoptium.net/en-GB/installation/)
3. Dependency: Apache Maven [Apache Maven](https://maven.apache.org/download.cgi)

## Generate Project
```cmd
mvn archetype:generate -DarchetypeGroupId=net.ltgt.gwt.archetypes -DarchetypeVersion=LATEST -DarchetypeArtifactId=modular-webapp
```
- **Note:** You can follow the instructions below:
```cmd
[INFO] Using property: module = App
Define value for property 'groupId': com.mycompany.mywebapp
Define value for property 'artifactId': mywebapp
Define value for property 'version' 1.0-SNAPSHOT: : HEAD-SNAPSHOT
Define value for property 'package' com.mycompany.mywebapp: : com.mycompany.mywebapp
Define value for property 'module-short-name' app: : MyWebApp
Confirm properties configuration:
module: App
groupId: com.mycompany.mywebapp
artifactId: mywebapp
version: HEAD-SNAPSHOT
package: com.mycompany.mywebapp
module-short-name: mywebapp
 Y: :         
```

## Run Project
1. Run client
   ```cmd
   mvn gwt:codeserver -pl *-client -am
   ```
   - Running on [http://localhost:9876](http://localhost:9876)
   
2. Run server
   ```cmd
   mvn jetty:run -pl *-server -am -Denv=dev
   ```
   - Running on [http://localhost:8080](http://localhost:8080)
