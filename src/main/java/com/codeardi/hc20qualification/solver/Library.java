package com.codeardi.hc20qualification.solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Library {

    private int id;
    private int signUpDays;
    private int booksPerDay;
    private int totalDays;
    private List<Book> books;
    private Set<Book> bookPool;

    public Library(int id, int signUpDays, int booksPerDay, int totalDays, List<Book> books, Set<Book> bookPool) {
        this.id = id;
        this.signUpDays = signUpDays;
        this.booksPerDay = booksPerDay;
        this.totalDays = totalDays;

        ArrayList<Book> inputBooks = new ArrayList<>(books);
        inputBooks.sort(Comparator.reverseOrder());

        int scanningDays = totalDays - signUpDays;
        this.books = inputBooks;

        this.bookPool = bookPool;
    }

    private boolean signingUp;

    private int remainingSignUpDays = signUpDays;
    private Set<Book> booksInScanning = new HashSet<>();
    private List<Book> scannedBooks = new ArrayList<>();

    public void startSigningUp() {
        this.signingUp = true;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void stopSigningUp() {
        this.signingUp = false;
    }

    public boolean isSigningUp() {
        return signingUp;
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

    public void scanTotalBooks(int idleDays) {
        int scanningDays = totalDays - idleDays - signUpDays;
        int manageableBooks = Math.min(scanningDays * booksPerDay, books.size());

        for (int i = 0; i < manageableBooks; i++) {
            if (!bookPool.contains(books.get(i))) {
                scannedBooks.add(books.get(i));
            }
        }
        bookPool.addAll(scannedBooks);
    }

    public int updateRemainingBooksAndReturnScore(int idleDays, Set<Book> scannedBooks) {
        int scanningDays = totalDays - idleDays - signUpDays;
        int manageableBooks = Math.min(scanningDays * booksPerDay, books.size());
        int totalBooksScore = 0;

        final Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext() && manageableBooks > 0) {
            final Book next = iterator.next();
            if (scannedBooks.contains(next)) {
                iterator.remove();
            } else {
                totalBooksScore += next.getScore();
                manageableBooks--;
            }
        }
        return totalBooksScore;
    }
}
