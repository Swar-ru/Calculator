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
            return calculateUnaryOperation(parts[0], parts[1]);
        }

        // Обработка бинарных операций
        return calculateBinaryOperation(parts[0], parts[1], parts[2]);
    }

    private double calculateUnaryOperation(String operator, String operandStr) {
        double operand = Double.parseDouble(operandStr);

        if (operator.equals("sqrt")) {
            SquareRoot squareRoot = new SquareRoot(operand);
            return squareRoot.execute(operand);
        }

        throw new IllegalArgumentException("Неизвестная унарная операция: " + operator);
    }

    private double calculateBinaryOperation(String operand1Str, String operator, String operand2Str) {
        double operand1 = Double.parseDouble(operand1Str);
        double operand2 = Double.parseDouble(operand2Str);

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

    public ExpressionValidator getValidator() {
        return validator;
    }
}
