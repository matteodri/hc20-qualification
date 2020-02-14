package com.codeardi.hc20qualification;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Util class to read and write lines to a file.
 */
public class FileUtils {

    public static List<String> readFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File doesn't exist or is not accessible.");
        }

        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            stream.forEach(s -> lines.add(s));
        }


        return lines;
    }

    public static void writeToFile(List<String> lines, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (String line : lines) {
            printWriter.println(line);
        }
        printWriter.close();
    }

}
