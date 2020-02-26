package com.codeardi.hc20qualification.solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        ArrayList<Library> libraries = new ArrayList<>();
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
            Library library = new Library(i, signupDays, booksPerDay, days, booksLibrary, bookPool);
            libraries.add(library);
        }

        List<Library> resultingLibraries = solve(booksNumber, librariesNumber, days, libraries);

        return createOutputFromLibraries(resultingLibraries);
    }

    /**
     * MAIN IMPLEMENTATION OF SOLUTION
     *
     * @return list of libraries picked. Each library will have internally the list of books to be scanned.
     */
    protected List<Library> solve(int numberOfBooks, int numberOfLibraries, int numberOfDays, List<Library> libraries) {
        List<Library> result = new ArrayList<>();

        List<Library> librariesLeftToProcess = libraries.stream()
            .filter(l -> l.getSignUpDays() < numberOfDays)
            .collect(Collectors.toList());

        int totalSignUpDays = 0;
        Set<Book> scannedBooks = new HashSet<>();

        while (totalSignUpDays < numberOfDays && librariesLeftToProcess.size() > 0) {

            Library libraryToProcess = getNextLibrary(totalSignUpDays, scannedBooks, librariesLeftToProcess);

            if (libraryToProcess == null){
                break;
            }

            int tempTotalSignUpDays = totalSignUpDays + libraryToProcess.getSignUpDays();
            if (tempTotalSignUpDays > numberOfDays) {
                break;
            }

            libraryToProcess.scanBooks(totalSignUpDays);
            List<Book> justScannedBooks = libraryToProcess.getScannedBooks();
            if (justScannedBooks.size() > 0) {
                totalSignUpDays += libraryToProcess.getSignUpDays();
                result.add(libraryToProcess);
                scannedBooks.addAll(justScannedBooks);
            }

            librariesLeftToProcess.remove(libraryToProcess);
        }

        int totalScore = result.stream()
            .filter(library -> library.getScannedBooks().size() > 0)
            .map(library -> library.getScannedBooksScore())
            .reduce(0, Integer::sum);

        logger.info("Total score of scanned books: {}", totalScore);
        return result;
    }

    private Library getNextLibrary(int totalSignUpDays, Set<Book> scannedBooks, List<Library> libraries) {
        double bestLibraryScore = 0;
        Library libraryWithBestScore = null;

        for (Library library : libraries) {
            long bookScore = library.updateRemainingBooksAndReturnScore(totalSignUpDays, scannedBooks);
            // CALCULATE LIBRARY SCORE
            double libraryScore = (double) bookScore / library.getSignUpDays();

            if (libraryScore > bestLibraryScore) {
                bestLibraryScore = libraryScore;
                libraryWithBestScore = library;
            }
        }

        return libraryWithBestScore;
    }


    private List<String> createOutputFromLibraries(List<Library> resultingLibraries) {
        List<String> resultingRows = new ArrayList<>();
        int totalLibraries = resultingLibraries.size();
        resultingRows.add(String.format("%d", totalLibraries));

        for (Library library : resultingLibraries) {
            resultingRows.add(String.format("%d %d", library.getId(), library.getScannedBooks().size()));

            String booksString = library.getScannedBooks().stream().map(Book::getId).map(id -> id.toString() + " ")
                .reduce("", String::concat);
            resultingRows.add(booksString);
        }

        return resultingRows;
    }
}
