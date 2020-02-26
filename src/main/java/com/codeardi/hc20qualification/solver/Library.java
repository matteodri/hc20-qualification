package com.codeardi.hc20qualification.solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Library {
    protected static final Logger logger = LogManager.getLogger(Library.class);

    private int id;
    private int signUpDays;
    private int booksPerDay;
    private int totalDays;
    private List<Book> books;
    private Set<Book> globalScannedBooks;
    private List<Book> scannedBooks = new ArrayList<>();

    public Library(int id, int signUpDays, int booksPerDay, int totalDays, List<Book> books,
        Set<Book> globalScannedBooks) {
        this.id = id;
        this.signUpDays = signUpDays;
        this.booksPerDay = booksPerDay;
        this.totalDays = totalDays;

        ArrayList<Book> inputBooks = new ArrayList<>(books);
        inputBooks.sort(Comparator.reverseOrder());
        this.books = inputBooks;

        this.globalScannedBooks = globalScannedBooks;
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getId() {
        return id;
    }

    public int getSignUpDays() {
        return signUpDays;
    }

    public List<Book> getScannedBooks() {
        return scannedBooks;
    }

    public int getScannedBooksScore() {
        return scannedBooks.stream()
            .mapToInt(Book::getScore)
            .sum();
    }

    public void scanBooks(int idleDays) {
        long scanningDays = Math.max(0, totalDays - idleDays - signUpDays);
        long scannableBooks = Math.min(scanningDays * booksPerDay, (long) books.size());

        for (int i = 0; i < scannableBooks; i++) {
            if (!globalScannedBooks.contains(books.get(i))) {
                scannedBooks.add(books.get(i));
            }
        }
        globalScannedBooks.addAll(scannedBooks);
    }

    public long updateRemainingBooksAndReturnScore(int idleDays, Set<Book> scannedBooks) {
        long scanningDays = Math.max(0, totalDays - idleDays - signUpDays);
        long scannableBooks = Math.min(scanningDays * booksPerDay, (long) books.size());

        int totalBooksScore = 0;

        final Iterator<Book> bookIterator = books.iterator();
        while (bookIterator.hasNext() && scannableBooks > 0) {
            final Book book = bookIterator.next();
            if (scannedBooks.contains(book)) {
                bookIterator.remove();
            } else {
                totalBooksScore += book.getScore();
                scannableBooks--;
            }
        }
        return totalBooksScore;
    }
}
