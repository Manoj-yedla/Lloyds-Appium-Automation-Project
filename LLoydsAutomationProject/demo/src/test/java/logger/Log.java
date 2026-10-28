package logger;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * 
 * A static logger function is used to display logs on console and append them in a log file.	
 * @author Harshit Jhureley
 *
 */
public class Log {

	//creating Logger instance and passing the class name in which Logger instance will be used
	static Logger Log=Logger.getLogger(Log.class.getName());
	
	public static void INFO(String Message){
	DOMConfigurator.configure("log4j.xml");	
	Log.info(Message);	
	}
	
	public static void ERROR(String Message){
	DOMConfigurator.configure("log4j.xml");		
	Log.error(Message);	
	
   }	

}
