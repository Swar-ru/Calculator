package org;

public class Multiplication extends Operation {
    public Multiplication(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double execute() {
        return operand1 * operand2;
    }

    @Override
    public String getOperationSymbol() {
        return "*";
    }
}
