package org;

public class Division extends Operation {
    public Division(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double execute() {
        if (operand2 == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        return operand1 / operand2;
    }

    @Override
    public String getOperationSymbol() {
        return "/";
    }
}
