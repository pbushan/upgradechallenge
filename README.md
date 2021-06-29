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
