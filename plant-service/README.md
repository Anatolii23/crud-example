# Plant-library


## Building and running the project

To build project by Maven execute the following command:
```bash
mvn clean install
```

Then run the project using the command below:
```bash
java -jar plant-api/target/plant-library.jar
```

As the result the process listens the HTTP 8081 port:
```
2019-03-27 11:38:29.866 - INFO : Tomcat started on port(s): 8081 (http) with context path '/api'
2019-03-27 11:38:29.870 - INFO : Started Application in 2.109 seconds (JVM running for 3.081)
```

## Building and running the project in docker

For running project in a Docker container you need:

Check if docker is installed:
```bash
docker -v
```

As a result command prompt should print message like this:
```
Docker version 18.09.3, build 774a1f4
```

Move to `provisioning/app/` folder and run:
```bash
./build.sh
```
and then
```bash
./run-daemon.sh
```

As a result after running the command `docker ps -a` you should see created postgres container with status `Up` in the listing.

# Openapi integration

To use swagger ui as openapi implementation follow the link `http://localhost:8081/api/swagger-ui.html`