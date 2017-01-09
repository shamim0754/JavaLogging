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
        System.out.println( "Hello World!" );
        logger.setLevel(Level.FINE);
        logger.warning("Simple log message");
    }
}
