package org;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExpressionValidator {

    public boolean isValidExpression(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        String cleanedInput = input.replaceAll("\\s+", "");

        Pattern pattern = Pattern.compile("^(-?\\d+(?:\\.\\d+)?)([+\\-*/])(-?\\d+(?:\\.\\d+)?)$");
        Matcher matcher = pattern.matcher(cleanedInput);

        return matcher.matches();
    }

    public String[] parseExpression(String input) {

        String cleanedInput = input.replaceAll("\\s+", "");

        Pattern pattern = Pattern.compile("^(-?\\d+(?:\\.\\d+)?)([+\\-*/])(-?\\d+(?:\\.\\d+)?)$");
        Matcher matcher = pattern.matcher(cleanedInput);

        if (matcher.find()) {
            String operand1 = matcher.group(1);
            String operator = matcher.group(2);
            String operand2 = matcher.group(3);

            return new String[]{operand1, operator, operand2};
        }

        throw new IllegalArgumentException("Не удалось разобрать выражение: " + input);
    }
}
