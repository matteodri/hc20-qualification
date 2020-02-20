package com.codeardi.hc20qualification.solver;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link SolverImpl}.
 */
class SolverImplTest {

    private SolverImpl unit;

    @Test
    @DisplayName("Output list not null")
    void getSolution() {
        unit = new SolverImpl();
        List<String> input = new ArrayList<>();

        input.add("6 2 7");
        input.add("1 2 3 6 5 4");
        input.add("5 2 2");
        input.add("0 1 2 3 4");
        input.add("4 3 1");
        input.add("3 2 5 0");

        List<String> output = unit.getSolution(input);

        assertNotNull(output);
    }

    @Test
    @DisplayName("Solve with library ordering same as input")
    void solve() {
        unit = new SolverImpl();
        List<String> input = new ArrayList<>();

        int numberOfBooks = 6;
        int numberOfLibraries = 2;
        int numberOfDays = 7;
        Set<Book> bookPool = new HashSet<>(Set.of(new Book(0, 1), new Book(1, 2),
            new Book(2, 3), new Book(3, 6), new Book(4, 5), new Book(5, 4)));

        List<Book> library0Books = new ArrayList<>(List.of(new Book(0, 1), new Book(1, 2),
            new Book(2, 3), new Book(3, 6), new Book(4, 5)));
        Library library0 = new Library(0, 2, 2, library0Books, bookPool);

        List<Book> library1Books = new ArrayList<>(List.of(new Book(3, 6), new Book(2, 3), new Book(5, 4), new Book(0, 1)));
        Library library1 = new Library(0, 2, 2, library0Books, bookPool);

        List<Library> libraries = List.of(library0, library1);

        // protected List<Library> solve(int numberOfBooks, int numberOfLibraries, int numberOfDays, List<Book> bookPool, List<Library> libraries){
        List<Library> resultLibraries = unit.solve(numberOfBooks, numberOfLibraries, numberOfDays, bookPool, libraries);

        assertNotNull(resultLibraries);

    }



}