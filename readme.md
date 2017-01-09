### Logging ###
Logging is the process of writing log messages during the execution of a program to a central place that offers quick debugging, easy maintenance.
But it have drawbacks also e.g It can slow down an application

Java Logging API was introduced in 1.4 in the `java.util.logging` package

There are many third party popular logging api

1. Log4J
2. SLF4J
3. Apache Commons Logging
4. LogBack 


### Java Logger ###
java.util.logging.Logger is the class used to log application messages in java logging API. Syntax to create logger

```java
private final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
```

### Logging Levels ###
There are seven levels of logging in java

SEVERE (highest) -> WARNING->INFO->CONFIG->FINE->FINER->FINEST

There are two other logging levels, `OFF` that will turn off `all` logging and ALL that will log all the messages.

Set logging level by following code
`logger.setLevel(Level.FINE);` 

The logs will be generated for all the levels equal to or greater than the logger level so it generates FINE,FINER,FINEST levels


### Warmup ###

1. create maven java project by following command

	`mvn archetype:generate -DgroupId=com.javaaround -DartifactId=Hibernate -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`

2. Update AppTest.java
	```java
	package com.javaaround;

	import org.junit.Test;;

	/**
	 * Unit test for simple App.
	 */
	/**
	 * Unit test for simple App.
	 */
	public class AppTest {
	   @Test
	   public void AppTest( ){
	        App.main(null);
	   }
	}
	```

3. Update junit version at pom.xml

	```xml
	<version>4.8.1</version>
	```

4. Run app by following command

	`mvn clean package`