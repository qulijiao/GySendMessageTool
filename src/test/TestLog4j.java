package test;

import org.apache.log4j.Logger;

public class TestLog4j {
	private static final Logger logger = Logger.getLogger(TestLog4j.class);  
    
    public static void main(String[] args) {  
        logger.debug(" This is debug!!!");  
        logger.info(" This is info!!!");  
        logger.warn(" This is warn!!!");  
        logger.error(" This is error!!!");  
        logger.fatal(" This is fatal!!!");  
    }  
}
