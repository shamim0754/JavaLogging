package com.javaaround;
import java.util.logging.*;
/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger logger = Logger.getLogger(App.class.getName());
    public static void main( String[] args )
    {
        
        logger.setLevel(Level.FINE);

        ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());

        logger.addHandler(handler);
        logger.log(Level.WARNING,"Simple log message");

    }
}
