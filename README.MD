-----------
java -jar time-source-rabbit-1.3.0.RELEASE.jar --server.port=7070 --spring.cloud.stream.bindings.output.destination=ticktock

java -jar target/scs-sink-stomp-multitopic-1.0.0-RELEASE.jar --spring.cloud.stream.defaultBinder=rabbit --spring.cloud.stream.bindings.input.destination=ticktock --logging.level.io.pivotal.spring.cloud.stream.sink=TRACE --stomp.withsockjs=true --stomp.topic-path=/dropoffDistrictCode 


## Test with http source and geocoding processor

```
java -jar target/scs-source-http-1.0.0-RELEASE.jar --spring.cloud.stream.defaultBinder=rabbit 

java -jar target/scs-processor-geocoding-reverse-1.0.0-RELEASE.jar --spring.cloud.stream.defaultBinder=rabbit --logging.level.io.pivotal.spring.cloud.stream.sink=TRACE --properties.mongo.hostName=localhost

java -jar target/scs-sink-stomp-multitopic-1.0.0-RELEASE.jar --spring.cloud.stream.defaultBinder=rabbit --logging.level.io.pivotal.spring.cloud.stream.sink=TRACE --stomp.withsockjs=true --stomp.topic-path=/dropoffDistrictCode

curl -H "Content-Type: text/plain" -X POST -d'468244D1361B8A3EB8D206CC394BC9E9,BB899DFEA9CC964B50C540A1D685CCFB,2013-01-01 00:00:00,2013-01-01 00:04:00,240,1.71,139.752268,35.677043,139.771699,35.697283,CSH,6.50,0.50,0.50,0.00,0.00,7.50' 'http://localhost:9000'

```

java -jar target/scs-sink-stomp-1.0.0-RELEASE.jar --spring.cloud.stream.defaultBinder=rabbit --spring.cloud.stream.bindings.input.destination=transform_geotuple --logging.level.io.pivotal.spring.cloud.stream.sink=TRACE --stomp.topic=toproute --stomp.withsockjs=true