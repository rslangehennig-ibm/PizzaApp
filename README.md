# PizzaApp Readme

## Build Container:
docker build -t imageName:imageTag ./source

## Run Container
docker run -d -p externalPort:8000 imageName:imageTag

## Access Container
- DEV - http://dev.demo.com:8001
- QA - http://qa.demo.com:8002
- PROD - http://demo.com:8003
