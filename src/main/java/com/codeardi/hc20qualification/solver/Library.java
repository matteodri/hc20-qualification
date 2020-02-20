package com.codeardi.hc20qualification.solver;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Library {

    private int id;
    private int signUpDays;
    private int booksPerDay;
    private List<Book> books;
    private Set<Book> bookPool;

    public Library(int id, int signUpDays, int booksPerDay, List<Book> books, Set<Book> bookPool) {
        this.id = id;
        this.signUpDays = signUpDays;
        this.booksPerDay = booksPerDay;
        this.books = books;
        this.books.sort(Comparator.naturalOrder());

        this.bookPool = bookPool;
    }

    private int remainingSignUpDays = signUpDays;
    private Set<Book> booksInScanning = new HashSet<>();
    private Set<Book> scannedBooks = new HashSet<>();

    public void dayElapsed() {
        if (remainingSignUpDays > 0) {
            remainingSignUpDays--;
        } else {
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
}
