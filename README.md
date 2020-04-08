# gs-spring-boot
This is Demo Code intended to:

Leverage `springboot` to create a minimal web application which listens for Requests on the default port 8888
On receipt of a request, query `Postgresql` on the default port of 5432 again using `springframework` this time jdbc 

Expected output in the browser is:

```
DB values:
Test[my_int=1000]
Test[my_int=1001]
Test[my_int=1002]
Test[my_int=1003]
Test[my_int=1004]
```

This work includes *solely code* and is derived from merging: 

https://github.com/spring-guides/gs-spring-boot
https://github.com/spring-guides/gs-relational-data-access

with some additions for compatibility with

https://github.com/postgres/postgres

You will need to Install & start Postgresql using the appropriate means for your platform see:[https://www.postgresql.org/download/]

The query is hardwired here:[https://github.com/djptek/gs-spring-boot/src/main/java/com/example/springboot/HelloController.java]

To setup Postgresql please set the privileges on this script:[https://github.com/djptek/gs-spring-boot/setup_hellodb.sh] such that the `postgres` user can run this script in the original directory in order to access the accompanying sql [https://github.com/djptek/gs-spring-boot/setup_hellodb.sql]

For License see the Upstream code license here:[https://github.com/djptek/gs-spring-boot/LICENSE.code.txt]

To Start the service, build with:

./gradlew build 

and then start using e.g. 

$ java -javaagent:elastic-apm-agent-1.15.0.jar \
    elastic.apm.service_name=HelloController \
    elastic.apm.server_url=http://localhost:8200 \
    elastic.apm.application_packages=com.example.HelloController \
    -jar git/gs-spring-boot/build/libs/spring-boot-0.0.1-SNAPSHOT.jar
