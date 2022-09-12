# CityList Backend Api

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
