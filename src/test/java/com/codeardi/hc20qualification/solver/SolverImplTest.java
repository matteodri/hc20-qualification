package com.codeardi.hc20qualification.solver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link SolverImpl}. These tests don't validate the output comprehansivelu, they are used just for
 * excluding obvious issues or for debugging.
 */
class SolverImplTest {

    private SolverImpl unit;

    @Test
    @DisplayName("Test getSolution")
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
        assertEquals(5, output.size());
    }

    @Test
    @DisplayName("Test solve")
    void solve() {
        unit = new SolverImpl();
        List<String> input = new ArrayList<>();

        int numberOfBooks = 6;
        int numberOfLibraries = 2;
        int numberOfDays = 7;
        Set<Book> bookPool = new HashSet<>();

        List<Book> library0Books = new ArrayList<>(List.of(new Book(0, 1), new Book(1, 2),
            new Book(2, 3), new Book(3, 6), new Book(4, 5)));
        Library library0 = new Library(0, 2, 2, numberOfDays, library0Books, bookPool);

        List<Book> library1Books = new ArrayList<>(
            List.of(new Book(3, 6), new Book(2, 3), new Book(5, 4), new Book(0, 1)));
        Library library1 = new Library(1, 3, 2, numberOfDays, library1Books, bookPool);

        List<Library> libraries = List.of(library0, library1);

        List<Library> resultLibraries = unit.solve(numberOfBooks, numberOfLibraries, numberOfDays, libraries);

        assertNotNull(resultLibraries);
        assertEquals(2, resultLibraries.size());
    }


}