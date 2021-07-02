# Upgrade - Performance Coding Challenge

### Summary

Included here, is a gatling performance test that tests a create user and get user endpoints.
The test will first create users and then use the DB to get userIds for the feeder files.

### Pre-Requisites
Please run the upgrade challenge apps and verify via swagger that the API endpoints work functionally.
Please have maven configured on your machine. For macos users, please use homebrew to install maven.

How to execute the test

Please run the following command to execute this test

`mvn clean gatling:test`

### Recommendations (Yet to complete)
1) Increase number of tomcat threads (currently set to 2) while keeping an eye out on app memory. This is set in application.yml

```
server:
  connection-timeout: 30s
  tomcat:
    threads:
      max: 2       ---> Increase this value
```
2) Increase the number of database connections set in the docker compose file. Keep an eye out on DB CPU and memory

```
  postgres:
    image: 'postgres:13.1'
    container_name: postgres
    volumes:
      - /var/lib/postgresql/data
    command: postgres -c shared_preload_libraries=pg_stat_statements -c pg_stat_statements.track=all -c 'max_connections=15'    --> Increase this value
```

