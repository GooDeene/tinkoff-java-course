package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task0 {
    public static void printHelloWorld() {
        Logger logger = LogManager.getLogger();
        logger.info("Привет, мир!");
    }
}
