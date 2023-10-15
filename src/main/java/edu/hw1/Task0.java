package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Task0 {
    public void printHelloWorld() {
        Logger logger = LogManager.getLogger();
        logger.info("Привет, мир!");
    }
}
