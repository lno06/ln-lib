# ln-lib

## Pre-requisites:
* gradle
* java 11

## Library contains:
- helpful classes
- design patterns I was using
- some best practices I wanted to share

## Contains some external libraries:
* JUnit5
* GeoTools

## Example of Spring Boot
- actuator
  - with build info in /info
  - it displays all endpoints in /actuator
  - display all health details
  - display git information
- swagger2
  - http://localhost:8080/v2/api-docs : returns json doc
  - http://localhost:8080/v3/api-docs : returns json doc
  - http://localhost:8080/swagger-ui/ : returns pretty UI
- Custom failure analyzer
- Basic Spring Boot Security example
- Basic test of lombok  
- Basic test of Selma mapper
  - selma is a fast mapper (https://javaetmoi.com/2015/09/benchmark-frameworks-java-mapping-objet)
  - selma is using annotation processing


TODO dans bestpractice
- feign with fake API
https://javatodev.com/consuming-rest-api-using-feign-client-in-spring-boot/
https://instantwebtools.net/fake-rest-api
- Controller Advice
https://javatodev.com/exception-handling-spring-boot/
- webflux
