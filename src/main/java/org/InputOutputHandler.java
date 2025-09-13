package org;
import java.util.Scanner;

public class InputOutputHandler {
    private Scanner scanner;
    private Calculator calculator;
    private ExpressionValidator validator;

    public InputOutputHandler() {
        this.scanner = new Scanner(System.in);
        this.calculator = new Calculator();
        this.validator = new ExpressionValidator();
    }

    public void start() {
        printWelcomeMessage();

        while (true) {
            String input = getInput();

            if (isExitCommand(input)) {
                printExitMessage();
                break;
            }

            if (isInputEmpty(input)) {
                printEmptyInputMessage();
                continue;
            }

            processExpression(input);
        }

        closeResources();
    }

    private void printWelcomeMessage() {
        System.out.println("Калькулятор");
        System.out.println("Введите выражение в формате: число оператор число");
        System.out.println("Поддерживаемые форматы: '5 + 3', '5+3', '10.5*2', '-5/2'");
        System.out.println("Доступные операторы: +, -, *, /, ^ (возведение в степень), % (Остаток от деления), sqrt (Извлечение квадратного корня)");
        System.out.println("Доступно использовать только один оператор в выражении");
        System.out.println("Для выхода введите 'exit'");
        System.out.println();
    }

    private String getInput() {
        System.out.print("Введите выражение: ");
        return scanner.nextLine().trim();
    }

    private boolean isExitCommand(String input) {
        return input.equalsIgnoreCase("exit");
    }

    private boolean isInputEmpty(String input) {
        return input.isEmpty();
    }

    private void printExitMessage() {
        System.out.println("Выход из калькулятора. До свидания!");
    }

    private void printEmptyInputMessage() {
        System.out.println("⚠️  Введите выражение");
    }

    private void processExpression(String input) {
        try {
            double result = calculator.calculate(input);
            printSuccessResult(input, result);
        } catch (IllegalArgumentException e) {
            printValidationError(e.getMessage());
        } catch (ArithmeticException e) {
            printArithmeticError(e.getMessage());
        } catch (Exception e) {
            printUnknownError(e.getMessage());
        }
        System.out.println();
    }

    private void printSuccessResult(String input, double result) {
        try {
            String[] parts = validator.parseExpression(input);
            if (validator.isUnaryOperation(parts[0])) {
                System.out.println("✅ " + formatUnaryResult(parts[0],
                        Double.parseDouble(parts[1]), result));
            } else {
                System.out.println("✅ " + formatBinaryResult(
                        Double.parseDouble(parts[0]),
                        Double.parseDouble(parts[2]),
                        parts[1],
                        result));
            }
        } catch (Exception e) {
            // Если не удалось красиво отформатировать, используем простой вывод
            System.out.println("✅ " + input + " = " + String.format("%.2f", result));
        }
    }

    private String formatBinaryResult(double operand1, double operand2, String operator, double result) {
        return String.format("%.2f %s %.2f = %.2f", operand1, operator, operand2, result);
    }

    private String formatUnaryResult(String operator, double operand, double result) {
        return String.format("%s %.2f = %.2f", operator, operand, result);
    }

    private void printValidationError(String message) {
        System.out.println("❌ " + message);
        System.out.println("💡 Примеры правильного формата:");
        System.out.println("   • 5 + 3    • 10*2.5    • 15/3");
        System.out.println("   • 2^3     • 10%3      • sqrt25");
    }

    private void printArithmeticError(String message) {
        System.out.println("❌ " + message);
    }

    private void printUnknownError(String message) {
        System.out.println("❌ Неизвестная ошибка: " + message);
    }

    private void closeResources() {
        scanner.close();
    }
}
