package com.codeardi.hc20qualification.solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if (inputLines.size() < 4) {
            throw new IllegalArgumentException("Expected 4 lines in input file. Cannot calculate a solution.");
        }
        String[] firstLine = inputLines.get(0).split(" ");

        int booksNumber = Integer.parseInt(firstLine[0]);
        int librariesNumber = Integer.parseInt(firstLine[1]);
        int days = Integer.parseInt(firstLine[2]);

        String[] scores = inputLines.get(1).split(" ");
        Set<Book> bookPool = new HashSet<>();

        for (int c = 0; c < booksNumber; c++) {
            bookPool.add(new Book(c, Integer.parseInt(scores[c])));
        }
        ArrayList<Library> libraries = new ArrayList<Library>();

        for (int i = 0; i < librariesNumber; i++) {
            int row = 2 + i * 2;
            String[] libraryLine = inputLines.get(row).split(" ");
            int booksInLibrary = Integer.parseInt(libraryLine[0]);
            int signupDays = Integer.parseInt(libraryLine[1]);
            int booksPerDay = Integer.parseInt(libraryLine[2]);

            String[] libraryBooks = inputLines.get(row + 1).split(" ");
            List<Book> booksLibrary = new ArrayList<>();
            for (int j = 0; j < booksInLibrary; j++) {
                int scoreIdx = Integer.parseInt(libraryBooks[j]);
                Book book = new Book(Integer.parseInt(libraryBooks[j]), Integer.parseInt(scores[scoreIdx]));
                booksLibrary.add(book);
            }
            Library library = new Library(i, signupDays, booksPerDay, booksLibrary, new HashSet<>());
            libraries.add(library);
        }


        List<Library> resultingLibraries = solve(booksNumber, librariesNumber, days, bookPool, libraries);
        return createOutput(resultingLibraries);
    }

    private List<String> createOutput(List<Library> resultingLibraries) {
        List<String> resultingRows = new ArrayList<>();
        int totalLibraries = resultingLibraries.size();
        resultingRows.add(String.format("%d", totalLibraries));

        for(int i = 0; i < resultingLibraries.size(); i++) {
            Library library = resultingLibraries.get(i);
            resultingRows.add(String.format("%d %d", library.getId(), library.getScannedBooks().size()));

            String booksString = library.getScannedBooks().stream().map(Book::getId).map(id -> id.toString() + " ")
                .reduce("", String::concat);
            resultingRows.add(booksString);
        }

        return resultingRows;
    }


    /**
     * MAIN IMPLEMENTATION OF SOLUTION
     *
     * @return list of libraries picked. then each library will have the list of books selected
     */
    protected List<Library> solve(int numberOfBooks, int numberOfLibraries, int numberOfDays, Set<Book> books, List<Library> libraries) {
        List<Library> result = new ArrayList<>();

        List<Library> librariesToProcess = pickListOfLibraries(numberOfBooks, numberOfLibraries, numberOfDays, books, libraries);
        logger.info("Finished identification of libraries");

        int totalSignUpDays = 0;
        for (Library library : librariesToProcess) {
            library.scanTotalBooks(numberOfDays, totalSignUpDays);
            if (library.getScannedBooks().size() > 0){
                totalSignUpDays += library.getSignUpDays();
                result.add(library);
            }
        }

        logger.info("Finished days process");

        int totalScore = 0;
        for (Library library : result) {
            if (library.getScannedBooks().size() > 0) {
                totalScore += library.getScannedBooksScore();
            }
        }

        logger.info("Total score of scanned books: {}", totalScore);
        return result;
    }

    /**
     * This method can be just picking libraries in order of input or be smarter to optimise score picking libraries with most valueable books and less sign up time
     *
     * @return libraries in order to be processed
     */
    private List<Library> pickListOfLibraries(int numberOfBooks, int numberOfLibraries, int numberOfDays, Set<Book> books, List<Library> libraries) {
        List<Library> results = new ArrayList<>();
        List<Library> orederedLibraries = new ArrayList<>(libraries);
        orederedLibraries.sort(Comparator.comparing(Library::getSignUpDays));

        int totalSignUpDays = 0;
        for (int i = 0; i < orederedLibraries.size(); i++) {
            totalSignUpDays += orederedLibraries.get(i).getSignUpDays();
            results.add(orederedLibraries.get(i));
            if (totalSignUpDays > numberOfDays) {
                break;
            }
        }
        return results;
    }

}
