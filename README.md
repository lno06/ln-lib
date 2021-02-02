# ln-lib

## Pre-requisites:
* gradle
* java 15

## Library contains:
- helpful classes
- design patterns I was using
- some best practices I wanted to share
- Examples of new features introduced in java 11/12/13/14/15

## Contains some external libraries:
* JUnit5
* GeoTools

## Example of Spring Boot
- First, run SpringBootApp with gradle (in order to have all git information)
- actuator
  - with build info in /info (http://localhost:8080/actuator/info)
  - it displays all endpoints in /actuator (http://localhost:8080/actuator)
  - display all health details (http://localhost:8080/actuator/health)
  - display git information (http://localhost:8080/actuator/info)
- swagger2
  - http://localhost:8080/v2/api-docs : returns json doc
  - http://localhost:8080/v3/api-docs : returns json doc
  - http://localhost:8080/swagger-ui/ : returns pretty UI
- Custom failure analyzer (see MyFailureAnalyzerController)
- Basic Spring Boot Security example (http://localhost:8080/requires-login)
- Basic test of lombok  
- Basic test of Selma mapper
  - selma is a fast mapper (https://javaetmoi.com/2015/09/benchmark-frameworks-java-mapping-objet)
  - selma is using annotation processing
- FeignClient: enables to easily write web service clients
  - It uses Spring Cloud OpenFeign
  - It connects to a fake API : https://instantwebtools.net/fake-rest-api
  - http://localhost:8080/airlines/1
  - http://localhost:8080/passengers?page=1&size=10

TODO dans bestpractice
- Controller Advice
https://javatodev.com/exception-handling-spring-boot/
- webflux
