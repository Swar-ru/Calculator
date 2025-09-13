package org;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Калькулятор");
        System.out.println("Введите выражение в формате: число оператор число");
        System.out.println("Поддерживаемые форматы: '5 + 3', '5+3', '10.5*2', '-5/2'");
        System.out.println("Доступные операторы: +, -, *, /");
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

            try {
                double result = calculator.calculate(input);

                String[] parts = new ExpressionValidator().parseExpression(input);
                double operand1 = Double.parseDouble(parts[0]);
                double operand2 = Double.parseDouble(parts[2]);
                String operator = parts[1];

                System.out.println(calculator.formatResult(operand1, operand2, operator, result));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Пожалуйста, используйте формат: число оператор число");
                System.out.println("Примеры: '5 + 3', '10*2.5', '15/3'");
            } catch (ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Неизвестная ошибка: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }
}
