package org;

public class Calculator {
    private ExpressionValidator validator;

    public Calculator() {
        this.validator = new ExpressionValidator();
    }

    public double calculate(String expression) {
        if (!validator.isValidExpression(expression)) {
            throw new IllegalArgumentException("Некорректное выражение: " + expression);
        }

        String[] parts = validator.parseExpression(expression);
        double operand1 = Double.parseDouble(parts[0]);
        double operand2 = Double.parseDouble(parts[2]);
        String operator = parts[1];

        Operation operation = createOperation(operand1, operand2, operator);
        return operation.execute();
    }

    private Operation createOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return new Addition(operand1, operand2);
            case "-":
                return new Subtraction(operand1, operand2);
            case "*":
                return new Multiplication(operand1, operand2);
            case "/":
                return new Division(operand1, operand2);
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }

    public String formatResult(double operand1, double operand2, String operator, double result) {
        return String.format("%s %s %s = %s",
                formatNumber(operand1),
                operator,
                formatNumber(operand2),
                formatNumber(result));
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.format("%d", (long) number);
        } else {
            return String.format("%.2f", number);
        }
    }
}
