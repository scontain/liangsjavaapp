# TL;DR

./start.sh

## Deploy app with `sconectl` (Postgresql still native)

Install `postgresql` with helm.

Note: If you do not have the bitnami Helm repo, install with `helm repo add bitnami https://charts.bitnami.com/bitnami`.

```bash
# user and password are defined in application.properties file.
helm install postgresql bitnami/postgresql \
    --set auth.username=dbuser \
    --set auth.password=dbpass \
    --set auth.database=dbnorris
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
