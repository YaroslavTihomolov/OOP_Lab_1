package org.ru.nsu.tikhomolov.lab1.ParseInputArgs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Pattern;

public class ParseInputArgs {
    private static final Pattern TXT_PATTERN = Pattern.compile(".txt$");

    private static boolean hasTxtExtension(String path) {
        return TXT_PATTERN.matcher(path).find();
    }

    public static InputStream getInputStream(String[] args) throws Exception {
        if (args.length == 0) {
            return System.in;
        }

        String path = String.join(" ", args);

        if (!hasTxtExtension(path)) {
            throw new Exception("Wrong file format.");
        }

        File file = new File(path);

        if (file.exists()) {
            return new FileInputStream(file);
        } else {
            throw new Exception("File " + path + " does not exist.");
        }
    }
}