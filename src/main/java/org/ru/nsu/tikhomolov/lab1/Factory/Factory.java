package org.ru.nsu.tikhomolov.lab1.Factory;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;
import org.apache.log4j.Logger;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Factory {
    private static final Logger logger = Logger.getLogger(Factory.class);

    private static Map<String, Constructor<?> > getMapClass() {
        logger.debug("Create map of constructors");
        Scanner scanner = null;
        try {
            InputStream conf_file = Factory.class.getClassLoader().getResourceAsStream("ClassOperation.txt");
            scanner = new Scanner(conf_file);
        } catch (Exception e) {
            logger.error("File ClassOperation.txt did not exist", new Exception(e));
        }
        Map<String, Constructor<?> > class_operation = new HashMap<>();
        String operation, tmp;
        while (scanner.hasNext()) {
            operation = scanner.next();
            tmp = scanner.next();
            try {
                class_operation.put(operation, Class.forName(tmp).getDeclaredConstructor(Calculator.Context.class));
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                logger.error(tmp + "is wrong path to class of " + operation + " operation" , new Exception(e));
            }
        }
        return class_operation;
    }

    private static final Map<String, Constructor<?> > class_operation = getMapClass();

    public static Object getInstance(String className, Calculator.Context value) throws InstantiationException,
            IllegalAccessException, InvocationTargetException {
        if (!class_operation.containsKey(className)) {
            logger.warn("Can not crete " + className + " instance");
        }
        try {
            logger.debug("Creating " + className + " instance");
            return class_operation.get(className).newInstance(value);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.warn("Can not crete " + className + " instance");
            return null;
        }
    }
}
