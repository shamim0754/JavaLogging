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
            FileHandler handler = new FileHandler("myapp-log.%u.%g.txt");
            handler.setFilter(new Filter() {
                @Override
                public boolean isLoggable(LogRecord record) {
                    Object[] objs = record.getParameters();
                    Person person = (Person) objs[0];
                    return person != null && person.getAge() > 30 ? true : false; 
                }
            });
            logger.addHandler(handler); 
            Person p1 = new Person("Shamim", 32);
            Person p2 = new Person("Alamin", 29);
            logger.log(Level.WARNING,"age=" + p1.getAge(),p1 );
            logger.log(Level.INFO,"age=" + p2.getAge(),p2);

    	}catch(Exception e){

    	}

    }
}
