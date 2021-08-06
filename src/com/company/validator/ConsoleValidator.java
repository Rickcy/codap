package com.company.validator;

import com.company.entity.AuctionDirection;

public class ConsoleValidator implements Validator<String> {

    private String message;
    public static final int LIMIT_LINES = 1_000_000;

    @Override
    public boolean validate(String input) {
        String[] words = input.split(" ");
        if (words.length != 3) {
            message = String.format("Значенений должно быть не больше и не меньше 3! (%s)", input);
            return false;
        }
        if (words[0].length() != 1 || AuctionDirection.create(words[0].charAt(0)) == null) {
            message = String.format("Направление указано не верно! (%s)", input);
            return false;
        }
        try {
            if (words[1].length() > 4 || Integer.parseInt(words[1]) > 1000 || Integer.parseInt(words[1]) < 1) {
                message = String.format("Количество должно быть от 0 до 1000 включительно! (%s)", input);
                return false;
            }
        } catch (NumberFormatException formatException) {
            message = String.format("Количество должно быть числом от 0 до 1000 включительно! (%s)", input);
            return false;
        }


        if (words[2].length() - (words[2].indexOf(".") + 1) > 2) {
            message = String.format("Цена должна быть с шагом 1 копейка! (%s)", input);
            return false;
        }
        try {
            if (Double.parseDouble(words[2]) > 100 || Double.parseDouble(words[2]) < 0.01) {
                message = String.format("Цена должна быть больше 0 и не больше 100 рублей включительно! (%s)", input);
                return false;
            }
        } catch (NumberFormatException formatException) {
            message = String.format("Количество должно быть числом от 0 и не больше 100 рублей включительно! (%s)", input);
            return false;
        }

        return true;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
