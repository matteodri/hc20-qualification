package com.codeardi.hc20qualification.solver;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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

        List<String> output = unit.getSolution(input);

        assertNotNull(output);
    }

}