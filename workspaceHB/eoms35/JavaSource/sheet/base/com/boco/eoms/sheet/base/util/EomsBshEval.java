package com.boco.eoms.sheet.base.util;

import bsh.EvalError;
import bsh.Interpreter;

public class EomsBshEval extends Interpreter {

    private static final long serialVersionUID = 1L;

    public EomsBshEval() {
        super();
    }

    public static boolean getbooleanFromStringExpression(String expression) throws EvalError {
        String[] expressionValues1 = expression.split("==");
        String[] expressionValues2 = expression.split("!=");
        if (expressionValues1.length == 2) {
            String leftValue = expressionValues1[0].trim();
            String rightValue = expressionValues1[1].trim();
            if (leftValue.equals(rightValue)) {
                return true;
            } else {
                return false;
            }

        } else if (expressionValues2.length == 2) {
            String leftValue = expressionValues2[0].trim();
            String rightValue = expressionValues2[1].trim();
            if (!leftValue.equals(rightValue)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
