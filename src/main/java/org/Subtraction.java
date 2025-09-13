package org;

public class Subtraction extends Operation {
    public Subtraction(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double execute() {
        return operand1 - operand2;
    }

    @Override
    public String getOperationSymbol() {
        return "-";
    }
}