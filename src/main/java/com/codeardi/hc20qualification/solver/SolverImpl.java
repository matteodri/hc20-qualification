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

    private List<Book> listOfBooksAlreadySelected = new ArrayList<>();

    @Override
    public List<String> getSolution(List<String> inputLines) {

        // TODO use inputLines to calculate solution

        List<String> outputLines = new ArrayList<>();
        return outputLines;
    }


    /**
     * MAIN IMPLEMENTATION OF SOLUTION
     *
     * @return list of libraries picked. then each library will have the list of books selected
     */
    private List<Library> solve(int numberOfBooks, int numberOfLibraries, int numberOfDays, List<Book> books, List<Library> libraries){
        List<Library> result = new ArrayList<>();

        List<Library> librariesToProcess = pickListOfLibraries(numberOfBooks, numberOfLibraries, numberOfDays, books, libraries);


        return result;
    }

    /**
     * This method can be just picking libraries in order of input or be smarter to optimise score picking libraries with most valueable books and less sign up time
     * @return libraries in order to be processed
     */
    private List<Library> pickListOfLibraries(int numberOfBooks, int numberOfLibraries, int numberOfDays, List<Book> books, List<Library> libraries) {
        List<Library> result = new ArrayList<>();

        // pick by input order
        result = new ArrayList<>(libraries);


        // pick by score of books in each library



        // pick maximising value (weight of books)/(sign up days)


        return result;
    }
}
