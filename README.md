# Spring Boot ActiveMQ demo

A Spring Boot application using embedded ActiveMQ to demo:

* Re-delivery delays
* Receive-and-reply pattern

### Run

Start the application and submit a message to see re-delivery with delay.

```sh
$ curl -XPOST http://localhost:8080 -d "hello"
```

### Reference Documentation
For further reference, please consider the following sections:

* [Spring for Apache ActiveMQ 5](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-activemq)

### Guides
The following guides illustrate how to use some features concretely:

* [Java Message Service API via Apache ActiveMQ Classic.](https://spring.io/guides/gs/messaging-jms/)

