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
        
        try{
        logger.setLevel(Level.FINE);
        //1024 * 1024 = 1kb
        FileHandler handler = new FileHandler("myapp-log.%u.%g.txt",1024 * 1024);
		//handler.setFormatter(new MyFormatter());

        logger.addHandler(handler);
        logger.log(Level.WARNING,"Simple log message");
    	}catch(Exception e){

    	}

    }
}
