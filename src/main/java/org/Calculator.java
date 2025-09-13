package org;

public class Calculator {
    private ExpressionValidator validator;

    public Calculator() {
        this.validator = new ExpressionValidator();
    }

    public double calculate(String expression) {
        if (!validator.isValidExpression(expression)) {
            throw new IllegalArgumentException("Некорректное выражение. Используйте формат: число оператор число");
        }

        String[] parts = validator.parseExpression(expression);

        // Обработка унарных операций (корень)
        if (validator.isUnaryOperation(parts[0])) {
            double operand = Double.parseDouble(parts[1]);
            SquareRoot squareRoot = new SquareRoot(operand);
            return squareRoot.execute(operand);
        }

        // Обработка бинарных операций
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
            case "^":
                return new Exponentiation(operand1, operand2);
            case "%":
                return new Modulo(operand1, operand2);
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }

    // Перегруженный метод для красивого форматирования
    public String formatResult(double operand1, double operand2, String operator, double result) {
        return String.format("%.2f %s %.2f = %.2f", operand1, operator, operand2, result);
    }

    public String formatResult(String operator, double operand, double result) {
        return String.format("%s %.2f = %.2f", operator, operand, result);
    }
}
