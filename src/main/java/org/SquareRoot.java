package org;

public class SquareRoot extends Operation {
    public SquareRoot(double operand1) {
        super(operand1, 0);
    }

    @Override
    public double execute() {
        if (operand1 < 0) {
            throw new ArithmeticException("Нельзя извлечь корень из отрицательного числа");
        }
        return Math.sqrt(operand1);
    }

    @Override
    public String getOperationSymbol() {
        return "sqrt";
    }

    // Перегружаем метод для работы с одним операндом
    public double execute(double operand) {
        if (operand < 0) {
            throw new ArithmeticException("Нельзя извлечь корень из отрицательного числа");
        }
        return Math.sqrt(operand);
    }
}
