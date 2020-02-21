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
    private int totalBooksScore;

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
        int manageableBooks = Math.min(Math.max(scanningDays * booksPerDay, Integer.MAX_VALUE), inputBooks.size());
        this.books = inputBooks.subList(0, manageableBooks);
        this.totalBooksScore = this.books.stream().mapToInt(Book::getScore).sum();

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

    public int getBooksScore() {
        return totalBooksScore;
    }

    public int getMaxScannableScoreRemaining(int totalDays, int idleDays) {
        int scanningDays = totalDays - idleDays - signUpDays;
        int manageableBooks = Math.min(scanningDays * booksPerDay, books.size());

        int score = 0;
        for(int i = 0; i < manageableBooks; i++) {
            if (!bookPool.contains(books.get(i))) {
                score += books.get(i).getScore();
            }
        }
        return score;
    }

    public void scanTotalBooks(int idleDays) {
        int scanningDays = totalDays - idleDays - signUpDays;
        int manageableBooks = Math.min(Math.max(scanningDays * booksPerDay, Integer.MAX_VALUE), books.size());

        for(int i = 0; i < manageableBooks; i++) {
            if (!bookPool.contains(books.get(i))) {
                scannedBooks.add(books.get(i));
            }
        }
        bookPool.addAll(scannedBooks);
    }

    public void dayElapsed() {
        if (signingUp && remainingSignUpDays > 0) {
            remainingSignUpDays--;
        } else if (remainingSignUpDays <= 0) {
            scannedBooks.addAll(booksInScanning);
            booksInScanning.clear();
            for (int i = booksInScanning.size(); i < booksPerDay; i++) {
                scanBook();
            }
        }

    }

    private void scanBook() {
        for (Book book : books) {
            if (bookPool.contains(book) && !booksInScanning.contains(book)) {
                bookPool.remove(book);
                booksInScanning.add(book);
            }
        }
    }

    public void updateRemainingBooks(int idleDays, List<Book> scannedBooks) {
        int scanningDays = totalDays - idleDays - signUpDays;
        int manageableBooks = Math.min(Math.max(scanningDays * booksPerDay, Integer.MAX_VALUE), books.size());

        final Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext() && manageableBooks > 0) {
            final Book next = iterator.next();
            if (scannedBooks.contains(next)) {
                iterator.remove();
                totalBooksScore -= next.getScore();
            } else {
                manageableBooks--;
            }
        }
    }
}
