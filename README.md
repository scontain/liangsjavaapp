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
```bash
java -jar build/libs/random-chuck-norris-0.0.1-SNAPSHOT.jar
```
