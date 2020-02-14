package com.codeardi.basefileprocessor.solver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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