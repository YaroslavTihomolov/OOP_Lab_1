package org.ru.nsu.tikhomolov.lab1;

import java.io.*;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.logging.*;
import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;
import java.io.ByteArrayOutputStream;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Program start");
        try {
            var a = new Calculator(args);
            a.doCalculation();
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
        }
        logger.info("Program successfully end");
    }
}

