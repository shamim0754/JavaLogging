package com.javaaround;
import java.util.logging.*;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger logger = Logger.getLogger(App.class.getName());
    public static void main( String[] args )
    {
        
        try{
            LogManager.getLogManager().readConfiguration(new FileInputStream("logger.properties"));
            logger.log(Level.WARNING,"log manager" );
            logger.log(Level.INFO,"looger messeage");

    	}catch(Exception e){

    	}

    }
}
