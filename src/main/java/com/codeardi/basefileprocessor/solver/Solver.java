package com.codeardi.basefileprocessor.solver;

import java.util.List;

public interface Solver {

    /**
     * From a list of text lines calculate a solution as a list of text lines.
     * @param inputLines input text
     * @return output text
     */
    List<String> getSolution(List<String> inputLines);
}
