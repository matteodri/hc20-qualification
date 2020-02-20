package com.codeardi.hc20qualification.solver;

/**
 *
 */
public class Book implements Comparable<Book> {

    private int id;

    public Book(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;

        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            '}';
    }

    @Override
    public int compareTo(Book book) {
        return id - book.getId();
    }
}
