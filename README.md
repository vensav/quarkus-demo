# quarkus-demo Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .


## Getting started

```shell script
cd ~/Documents/IdeaProjects && \
mvn io.quarkus.platform:quarkus-maven-plugin:2.10.1.Final:create \
    -DprojectGroupId=com.vensav.quarkus \
    -DprojectArtifactId=quarkus-demo
```

### Additional setup On Mac
setting up graalvm and environment variables
- `sudo xattr -r -d com.apple.quarantine /Library/Java/JavaVirtualMachines/graalvm-ce-java17-22.1.0`
- `export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java17-22.1.0/Contents/Home`
- `export PATH=$PATH:$GRAALVM_HOME/bin`
- `source ~/.zshrc && gu install native-image`


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.


## Packaging and running the application
- The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.
The application is now runnable using 
```shell script
java -jar target/quarkus-app/quarkus-run.jar
```

## Packaging and running the application using uber jar
- If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```
- The application, packaged as an _über-jar_, is now runnable using 
```shell script
java -jar target/*-runner.jar
```

## Creating a native executable

- You can create a native executable using: 
```shell script
./mvnw package -Pnative
```
- You can then execute your native executable with: 
```shell script
./target/quarkus-demo-1.0.0-SNAPSHOT-runner
```
If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.


## Docker Image Build and Run

### Create native container build
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

### Run native
```shell script
docker build -f src/main/docker/Dockerfile.native -t quarkus-demo-native .
docker run -i --rm -p 8080:8080 quarkus-demo-native
```

### Run native-micro
```shell script
docker build -f src/main/docker/Dockerfile.native-micro -t quarkus-demo-native-micro .
docker run -i --rm -p 8080:8080 quarkus-demo-native-micro
```


## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

