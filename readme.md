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

### Logging Method ###

There are two different styles of logging methods like warning methods

`void warning(String sourceClass, String sourceMethod, String msg);`

`void warning(String msg);`

others methods : 

entering(String sourceClass, String sourceMethod);
entering(String sourceClass, String sourceMethod, Object param1);
entering(String sourceClass, String sourceMethod, Object[] params);

exiting (String sourceClass, String sourceMethod);
exiting (String sourceClass, String sourceMethod, Object result);

fine    (String message);
finer   (String message);
finest  (String message);

config  (String message);
info    (String message);
warning (String message);
severe  (String message);

There are one common method to log any message

log  (Level level, String message);
logp (Level level, String sourceClass, String sourceMethod, String msg);
logrb(Level level, String sourceClass, String sourceMethod,
    String bundle, String msg);

### Warmup ###

1. create maven java project by following command

	`mvn archetype:generate -DgroupId=com.javaaround -DartifactId=JavaLogging -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`

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

###  Hello Logging  ###	

Update App.java

```java
private static Logger logger = Logger.getLogger(App.class.getName());
public static void main( String[] args ){
	logger.setLevel(Level.FINE);
	logger.warning("Hello World log message");

	//recommended method to use
	logger.log(Level.WARNING,"Hello World log message");
}
```

### Logging Handlers ###

The handler receives the log message from the logger and exports it to a certain target.

Java SE provides the following Handlers:

1. StreamHandler: A simple handler for writing formatted records to an OutputStream.
2. ConsoleHandler: A simple handler for writing formatted records to System.err
3. FileHandler: A handler that writes formatted log records either to a single file, or to a set of rotating log files.
4. SocketHandler: A handler that writes formatted log records to remote TCP ports.
5. MemoryHandler: A handler that buffers log records in memory.

### Custom Handlers ###
To create our own Handler class, we need to extend java.util.logging.Handler class or any of it’s subclasses like StreamHandler, SocketHandler 

```java
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class MyHandler extends StreamHandler {

    @Override
    public void publish(LogRecord record) {
        //add own logic to publish
        super.publish(record);
    }


    @Override
    public void flush() {
        super.flush();
    }


    @Override
    public void close() throws SecurityException {
        super.close();
    }

}
```

### Formatters ###
Formatters are used to format the log messages. There are two available formatters in java logging API.

1. SimpleFormatter: This formatter generates text messages with basic information. ConsoleHandler uses this formatter class to print log messages to console.
2. XMLFormatter: This formatter generates XML message for the log, FileHandler uses XMLFormatter as a default formatter.

Update App.java

```java
logger.setLevel(Level.FINE);
ConsoleHandler handler = new ConsoleHandler();
handler.setFormatter(new SimpleFormatter());

logger.addHandler(handler);
```

Run app by following command

	`mvn clean package`

For XMLFormatter update App.java

`new XMLFormatter()`

Run app by following command

	`mvn clean package`

### Custom Formatters ###

We can create our own custom Formatter class by extending java.util.logging.Formatter class and attach it to any of the handlers

Create MyFormatter.java

```java
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return record.getThreadID()+"::"+record.getSourceClassName()+"::"
                +record.getSourceMethodName()+"::"
                +new Date(record.getMillis())+"::"
                +record.getMessage()+"\n";
    }

}
```

update App.java

`new MyFormatter()`

Run app by following command

	`mvn clean package`

### FileHander ###
The FileHandler writes all messages to file.By default the FileHandler uses the XMLFormatter 

```java
FileHandler handler = new FileHandler();
pattern=file name pattern
FileHandler handler = new FileHandler(String pattern);
append = whether the FileHandler should append to any existing files or not.
FileHandler handler = new FileHandler(String pattern, boolean append);
//limit = file size .if size reach new file create automatically
//count = number of file create
FileHandler handler = new FileHandler(String pattern, int limit, int count);
FileHandler handler = new FileHandler(String pattern, int limit, int count,
    boolean append);
```
### File Name Pattern ###

Pattern          |  Description
----------------- | -------------

/	| The file name separator of the system. Typically either \ or / .
%t	 | The temp directory of the system.
%h	| The user home directory of the system.
%g	| The generation number that distinguishes the rotated log files from each other.
%u	| A unique number to avoid naming conflicts.
%%	| A single percent sign, in case you want to use that in your file name.

Update App.java
```java
 try{
	logger.setLevel(Level.FINE);
	//1024 * 1024 = 1kb
	FileHandler handler = new FileHandler("myapp-log.%u.%g.txt",1024 * 1024);

	logger.addHandler(handler);
	logger.log(Level.WARNING,"Simple log message");
}catch(Exception e){

}
```
myapp-log.0.0.txt create automatically
```txt
?xml version="1.0" encoding="windows-1252" standalone="no"?>
<!DOCTYPE log SYSTEM "logger.dtd">
<log>
<record>
  <date>2017-01-10T12:45:37</date>
  <millis>1484030737804</millis>
  <sequence>0</sequence>
  <logger>com.javaaround.App</logger>
  <level>WARNING</level>
  <class>com.javaaround.App</class>
  <method>main</method>
  <thread>1</thread>
  <message>Simple log message</message>
</record>
</log>

```
### Log Manager ###

java.util.logging.LogManager is the class that reads the logging configuration, create and maintains the logger instances

```java
LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
```
myLogging.properties

```txt
handlers= java.util.logging.ConsoleHandler

.level= FINE

# default file output is in user's home directory.
java.util.logging.FileHandler.pattern = %h/java%u.log
java.util.logging.FileHandler.limit = 50000
java.util.logging.FileHandler.count = 1
java.util.logging.FileHandler.formatter = java.util.logging.XMLFormatter

# Limit the message that are printed on the console to INFO and above.
java.util.logging.ConsoleHandler.level = INFO
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter

com.journaldev.files = SEVERE
```

