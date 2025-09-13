package org;

public class Exponentiation extends Operation {
    public Exponentiation(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double execute() {
        return Math.pow(operand1, operand2);
    }

    @Override
    public String getOperationSymbol() {
        return "^";
    }
}
