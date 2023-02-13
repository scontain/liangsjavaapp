# Confidential Chuck Norris Jokes API

In this repo we show how to sconify a Java application with Scontain tools for lift-and-shifting applications.

This is the Confidential Chuck Norris Jokes API. Enjoy! If you find some bug, do like Chuck Norris. He doesn't need a debugger, he just stares down the bug until the code confesses.

## Deploy with `sconify-image`

./start.sh

## Deploy app with `sconectl` (Postgresql still native)

Install `postgresql` with helm.

Note: If you do not have the bitnami Helm repo, install with `helm repo add bitnami https://charts.bitnami.com/bitnami`.

```bash
# user and password are defined in application.properties file.
helm install postgres bitnami/postgresql \
    --set auth.username=chuck \
    --set auth.password=securepass123 \
    --set auth.database=chucknorris
```

Execute `run.sh` to execute `sconectl` applies and deploy the confidential app.

```bash
export RELEASE=chuck-norris-jokes
./run.sh
```

See the jokes in the logs.

```bash
kubectl logs -f chuck-norris-jokes<TAB>
```

---

## Test application Locally (Native version)

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
java -Dapplication.gossip.url=http://localhost:8080/api/jokes/random -jar build/libs/random-chuck-norris-0.0.1-SNAPSHOT.jar
```

Start the second server like this:

```bash
java -Dapplication.gossip.url=http://localhost:8081/api/jokes/random -Dserver.port=8081 -jar build/libs/random-chuck-norris-0.0.1-SNAPSHOT.jar
```
