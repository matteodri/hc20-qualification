package com.codeardi.hc20qualification.solver;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of a solution.
 */
public class SolverImpl implements Solver {

    protected static final Logger logger = LogManager.getLogger(SolverImpl.class);

    @Override
    public List<String> getSolution(List<String> inputLines) {

        // TODO use inputLines to calculate solution

        List<String> outputLines = new ArrayList<>();
        return outputLines;
    }


    /**
     *
     * @return list of libraries picked. then each library will have the list of books selected
     */
    private List<Library> solve(int numberOfBooks, int numberOfLibraries, int numberOfDays, List<Book> books, List<Library> libraries){
        List<Library> result = new ArrayList<>();




        return result;
    }
}
