package org;

public class Modulo extends Operation {
    public Modulo(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double execute() {
        if (operand2 == 0) {
            throw new ArithmeticException("Деление на ноль в операции остатка");
        }
        return operand1 % operand2;
    }

    @Override
    public String getOperationSymbol() {
        return "%";
    }
}
