package zorkLogger;

import java.io.File;
import org.apache.log4j.xml.DOMConfigurator;

public class LoggerHistory {

	
	public static void loggerConfig(){
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "log4j.xml";
		DOMConfigurator.configure(log4jConfigFile);
		
	}

}