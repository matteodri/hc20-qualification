package com.codeardi.hc20qualification.solver;

import java.util.ArrayList;
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
            Library library = new Library(i, signupDays, booksPerDay, booksLibrary, bookPool);
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
            Library library = resultingLibraries.get(0);
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
        boolean signingUpLibrary = false;
        for(int day = 0; day < numberOfDays; day++) {
            for (int i = 0; i < librariesToProcess.size(); i++) {
                Library library = librariesToProcess.get(i);
                boolean signingUp = library.isSigningUp();
                if (!signingUpLibrary && !signingUp) {
                    library.startSigningUp();
                    signingUpLibrary = true;
                }
                library.dayElapsed();
                if (signingUp && !library.isSigningUp()) {
                    library.stopSigningUp();
                    signingUpLibrary = false;
                }
            }
        }
        for (int i = 0; i < librariesToProcess.size(); i++) {
            Library library = librariesToProcess.get(i);
            if (library.getScannedBooks().size() > 0) {
                result.add(library);
            }
        }

        return result;
    }

    /**
     * This method can be just picking libraries in order of input or be smarter to optimise score picking libraries with most valueable books and less sign up time
     *
     * @return libraries in order to be processed
     */
    private List<Library> pickListOfLibraries(int numberOfBooks, int numberOfLibraries, int numberOfDays, Set<Book> books, List<Library> libraries) {
        List<Library> result = new ArrayList<>();

        // pick by input order
        result = new ArrayList<>(libraries);


        // pick by score of books in each library


        // pick maximising value (weight of books)/(sign up days)


        return result;
    }
}
