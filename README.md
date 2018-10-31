# MicronautZipkinEurekaIssue
Sample project where zipkin + eureka fails to serve any requests

## Steps to reproduce incompatibility:
1. docker run -p 9001:8761 springcloud/eureka
2. docker run -p 9411:9411 openzipkin/zipkin
3. ./gradlew run
4. curl http://localhost:8080/books

### Result:
{"_links":{"self":{"href":"/books","templated":false}},"message":"Page Not Found"}

## Eureka works without Zipkin:

1. git checkout eureka-working
2. ./gradlew run
3. curl http://localhost:8080/books

### Result:
[{"isbn":"1491950358","name":"Building Microservices"},{"isbn":"1680502395","name":"Release It!"},{"isbn":"0321601912","name":"Continuous Delivery:"}]


## Zipkin works without Eureka:

1. git checkout zipkin-working
2. ./gradlew run
3. curl http://localhost:8080/books

### Result:
[{"isbn":"1491950358","name":"Building Microservices"},{"isbn":"1680502395","name":"Release It!"},{"isbn":"0321601912","name":"Continuous Delivery:"}]

You can also look at Zipkin to see that the request passed.
