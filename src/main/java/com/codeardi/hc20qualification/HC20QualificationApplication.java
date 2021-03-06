package com.codeardi.hc20qualification;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codeardi.hc20qualification.solver.Solver;
import com.codeardi.hc20qualification.solver.SolverImpl;

/**
 * Spring Boot application entrypoint.
 */
@SpringBootApplication
public class HC20QualificationApplication {

    private static final Logger logger = LogManager.getLogger(HC20QualificationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HC20QualificationApplication.class, args);

        if (args.length == 2 && !args[0].isBlank() && !args[1].isBlank()) {
            long start = System.currentTimeMillis();

            process(args[0], args[1]);

            logger.info("Execution time: {}ms", System.currentTimeMillis() - start);
        } else {
            logger.error("Two arguments expected: [input file] [output file]\nTerminating execution.");
            return;
        }

    }

    private static void process(String inputFilePath, String outputFilePath) {
        List<String> inputLines;
        try {

            inputLines = FileUtils.readFromFile(inputFilePath);
            logger.info("Read {} lines from '{}'", inputLines.size(), inputFilePath);

        } catch (Exception e) {
            logger.error("Could not read file '{}'. Verify that file exists and is readable.", inputFilePath);
            logger.debug("Could not read file", e);
            return;
        }

        Solver solver = new SolverImpl();

        List<String> outputLines = solver.getSolution(inputLines);

        try {

            FileUtils.writeToFile(outputLines, outputFilePath);
            logger.info("Written {} lines to '{}'", outputLines.size(), outputFilePath);

        } catch (Exception e) {
            logger.error("Could not write to file '{}'. Verify that file doesn't already exists or is writable.",
                outputFilePath);
            logger.debug("Could not write to file", e);
            return;
        }
    }


}