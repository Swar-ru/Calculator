package org;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        ExpressionValidator validator = new ExpressionValidator();

        System.out.println("Калькулятор");
        System.out.println("Введите выражение в формате: число оператор число");
        System.out.println("Поддерживаемые форматы: '5 + 3', '5+3', '10.5*2', '-5/2'");
        System.out.println("Доступные операторы: +, -, *, /, ^ (возведение в степень), % (Остаток от деления), sqrt (Извлечение квадратного корня)");
        System.out.println("Доступно использовать только один оператор в выражении");
        System.out.println("Для выхода введите 'exit'");
        System.out.println();

        while (true) {
            System.out.print("Введите выражение: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход из калькулятора");
                break;
            }
            if (input.isEmpty()) {
                System.out.println("⚠️  Введите выражение");
                continue;
            }

            try {
                double result = calculator.calculate(input);
                String[] parts = validator.parseExpression(input);

                if (validator.isUnaryOperation(parts[0])) {
                    System.out.println("✅ " + calculator.formatResult(parts[0],
                            Double.parseDouble(parts[1]), result));
                } else {
                    System.out.println("✅ " + calculator.formatResult(
                            Double.parseDouble(parts[0]),
                            Double.parseDouble(parts[2]),
                            parts[1],
                            result));
                }

            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
                System.out.println("💡 Примеры правильного формата:");
                System.out.println("   • 5 + 3    • 10*2.5    • 15/3");
                System.out.println("   • 2^3     • 10%3      • sqrt25");
            } catch (ArithmeticException e) {
                System.out.println("❌ " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Неизвестная ошибка: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }
}