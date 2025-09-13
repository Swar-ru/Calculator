package org;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExpressionValidator {

    public boolean isValidExpression(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        // Удаляем лишние пробелы
        String trimmedInput = input.trim();

        // Проверка для унарных операций (корень) - используем "sqrt"
        if (trimmedInput.startsWith("sqrt")) {
            String numberPart = trimmedInput.substring(4).trim();
            // Убедимся, что после sqrt есть число
            if (numberPart.isEmpty()) {
                return false;
            }
            try {
                Double.parseDouble(numberPart);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // Регулярное выражение для поиска операторов с пробелами или без
        Pattern pattern = Pattern.compile("([-+]?\\d*\\.?\\d+)\\s*([+\\-*/^%])\\s*([-+]?\\d*\\.?\\d+)");
        Matcher matcher = pattern.matcher(trimmedInput);

        if (matcher.matches()) {
            try {
                Double.parseDouble(matcher.group(1));
                Double.parseDouble(matcher.group(3));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return false;
    }

    public String[] parseExpression(String input) {
        String trimmedInput = input.trim();

        // Обработка унарных операций (корень)
        if (trimmedInput.startsWith("sqrt")) {
            String numberPart = trimmedInput.substring(4).trim();
            if (numberPart.isEmpty()) {
                throw new IllegalArgumentException("После sqrt должно быть число");
            }
            return new String[]{"sqrt", numberPart};
        }

        // Разбор выражений с операторами
        Pattern pattern = Pattern.compile("([-+]?\\d*\\.?\\d+)\\s*([+\\-*/^%])\\s*([-+]?\\d*\\.?\\d+)");
        Matcher matcher = pattern.matcher(trimmedInput);

        if (matcher.find()) {
            String operand1 = matcher.group(1);
            String operator = matcher.group(2);
            String operand2 = matcher.group(3);
            return new String[]{operand1, operator, operand2};
        }

        throw new IllegalArgumentException("Не удалось разобрать выражение: " + input);
    }

    public boolean isUnaryOperation(String operator) {
        return operator.equals("sqrt");
    }
}
