package com.filesheriff;

import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Component
public class MyLogger {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void log(Level level, String message) {
        this.getLogger().log(level, message);
    }

    public void logInfo(String message) {
        LOGGER.log(Level.INFO, message);
    }

    private Logger getLogger() {
        LogManager logManager = LogManager.getLogManager();
        return logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }


}
