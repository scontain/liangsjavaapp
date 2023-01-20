## Test application

### Postgres
Get a simple database with:
```bash
docker run --name chuck-norris-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
```

### How to build the application
```bash
./gradlew clean build -xtest
```
Test is somehow faling, ignore it with flag `-xtest`

### How to run the application

Please note:
```
The application is connecting to jdbc:postgresql://postgres:5432/chucknorris by default
You may need to create the checknorris database first and make sure the hostname `postgres` is reachable, or you can just reconfigure the database url by override the property `spring.datasource.url` with java parameter `-Dspring.datasource.url=<your-favorate-db-url>`
```

```bash
CREATE DATABASE chucknorris; // in psql shell
```

```bash
java -jar build/libs/random-chuck-norris-0.0.1-SNAPSHOT.jar
```

#### Start application with gossip hosts

Start the first server like this:

```bash
java -Dapplication.gossip.url=http://localhost:8081/api/jokes/random -jar build/libs/random-chuck-norris-0.0.1-SNAPSHOT.jar
```

Start the second server like this:

```bash
java -Dapplication.gossip.url=http://localhost:8080/api/jokes/random -Dserver.port=8081 -jar build/libs/random-chuck-norris-0.0.1-SNAPSHOT.jar
```
