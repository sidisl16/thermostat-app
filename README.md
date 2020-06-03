# thermostat-app
A small iot application to monitor temerature coming out from the various IOT devices built using MQTT for transport ,google's protobuf for message serialization in Java Springboot and Mongo Db for data storage.

This application has following feature
1. Device provisioning - This application exposes a common provisioning topic where device has to register themselve to this application
2. Configuration - User can configure the device from this application e.g. setting up data frequencey or want to change the topic to which device is publishing data etc.  
3. Data - This application stores data in time series format which uses Size bucketing schema design in Mongo db and allows user to search within specific date range.

Note: This application is in implementation phase.

