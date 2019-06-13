package com.lyf.demo.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultLogger {

    private static Logger logger = LogManager.getLogger(DefaultLogger.class);

    public static void main(String[] args) {
        logger.debug("This is default logger ! Debug Time : {}", System.currentTimeMillis());
        logger.error("This is default logger ! Error Time : {}", System.currentTimeMillis());
    }
}
