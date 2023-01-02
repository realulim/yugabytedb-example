# yugabytedb-example

## Build and run Example App

docker and docker-compose must be in the PATH

* ./gradlew composeBuild
* docker-compose up -d
* wait for database to be up (docker logs -f yugabytedb)
* ./gradlew bootRun
