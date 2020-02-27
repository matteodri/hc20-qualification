package com.codeardi.hc20qualification.solver;

public class Book implements Comparable<Book> {

    private int id;
    private int score;

    public Book(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
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
    public int compareTo(Book book) {
        return book.getScore() - score;
    }
}
