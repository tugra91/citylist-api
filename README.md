# CityList Backend API

## Stack

- Spring Boot 2.6.5
- MongoDB
- Maven
- Docker

## Install instructors

### Prerequirement
- Docker
- Maven

### Prior Knowledge

Opened the terminal and then please type commands in below. It will be installed and prepared MongoDB to use in the application through below commands. 

### Create and Run DB without any user configuration.
```shell
docker run --name kuehneExampleDb -d -p 27017:27017 -v ~/Dev/kuehne-nagel/db:/data/db -e MONGO_INITDB_ROOT_USERNAME=cityListAdmin -e MONGO_INITDB_ROOT_PASSWORD=kuehneNagel mongo:latest
```
### Execute below command to connect mongodb which is inside the docker deamon. 
```shell
docker exec -i -t kuehneExampleDb mongo --username cityListAdmin --password kuehneNagel --authenticationDatabase admin
```
### Execute below command to create mongo user to connect from application. 
```shell
db.createUser({user:"cityListUser", pwd:"kuehneNagel", roles:[{role:"readWrite", db:"cityList"}], passwordDigestor:"server"});
```
After all of acts, you might start application with debug mode and enjoy it. :) 

## Key Points

It fetchs all of cities data from cvs file to initialize and load dataList to database. It do it in the CityListService class. If cvs datas had recorded before, initial method doesn't do anything. 

In Search was used async and multi-threading technologies through this method can be search the text with two way when user insert a searchText. When a user start a search process, firstly method checks the text for whether number or alphanumeric and then If text is a number, method search the text for cityId and cityName on the other hand If text is a alphanumeric, method search the text for only cityName. In all of process, Both method work separete each other so async.  
