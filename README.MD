# I. Quick Start Steps 
## 1. Prerequisite

* [Homebrew](http://brew.sh/) if you use Mac OS X and prefer homebrew otherwise you need to install/start all the middleware components manually.
* [Homebrew Services](https://github.com/Homebrew/homebrew-services)
* [Maven](http://brewformulas.org/Maven)
* [RabbitMQ](http://brewformulas.org/Rabbitmq)

### 1-1. Start RabbitMQ
```
brew services start rabbitmq
```


## 2. Download and Build

```
git clone https://github.com/komushi/scs-sink-stomp-multitopic.git
cd scs-sink-stomp-multitopic
mvn clean package docker:build
docker push komushi/scs-sink-stomp-multitopic
```


## 3. Run in Java CLI
### 3-1. Command to start jar
```
java -jar target/scs-sink-stomp-multitopic-1.0.0.RELEASE.jar --spring.cloud.stream.defaultBinder=<rabbit/kinesis/kafka>
```

### 3-2. Test with scs-source-http and scs-processor-geocoding-reverse
```
java -jar time-source-rabbit-1.3.0.RELEASE.jar --server.port=7070 --spring.cloud.stream.bindings.output.destination=ticktock

java -jar target/scs-sink-stomp-multitopic-1.0.0-RELEASE.jar --spring.cloud.stream.bindings.input.destination=ticktock --logging.level.info.cloudnative=TRACE
```

## 4. Run with docker deployment
### 4-1. Deploy on AWS
```
docker run komushi/scs-sink-stomp-multitopic --spring.cloud.stream.defaultBinder=kinesis --cloud.aws.region.static=ap-northeast-1 --gemfire.pool.host-addresses=<gemfire-cacheserver-address>:40404 
```

### 4-2. Local Deployment
```
docker run komushi/scs-sink-stomp-multitopic --spring.rabbitmq.host=docker.for.mac.localhost --logging.level.info.cloudnative=TRACE
```

# II. Appendix
### Parameters - application.properties
```
server.port=9210
spring.cloud.stream.defaultBinder=rabbit
spring.cloud.stream.bindings.input.destination=transform_geotuple
spring.cloud.stream.bindings.input.group=1
cloud.aws.region.static=ap-northeast-1
stomp.withsockjs=true
stomp.topicPath=/dropoffDistrictCode
```
