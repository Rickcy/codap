package com.company;

import com.company.app.Auction;
import com.company.app.console.ConsoleAuction;
import com.company.entity.OptimalBidResult;
import com.company.validator.ConsoleValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileExportApplication {

    //    -f /абсолютный/путь/к/файлу
    public static void main(String[] args) {
        if (args.length != 2) System.out.println("Не указан путь к файлу");
        Option option = new Option(args[0].trim(), args[1].trim());
        if (!option.getFlag().equals("-f")) System.out.println("Не верный флаг");
        if (option.getOpt().isEmpty()) System.out.println("Путь до файла не может быть пустым");

        ConsoleValidator validator = new ConsoleValidator();
        StringBuilder rawData = new StringBuilder();
        File file = new File(option.getOpt());

        try (FileReader fr = new FileReader(file)) {

            BufferedReader reader = new BufferedReader(fr);
            String currentLine = reader.readLine();
            int countLine = 0;
            while (currentLine != null) {
                if (countLine >= ConsoleValidator.LIMIT_LINES) {
                    System.out.printf("\nПривышен лимит данных! (лимит %d)", ConsoleValidator.LIMIT_LINES);
                    return;
                } else if (validator.validate(currentLine)) {
                    rawData.append(currentLine);
                    rawData.append(";");
                    countLine++;
                } else {
                    System.out.println("\nНекоректно введенные входные данные:");
                    System.out.println(validator.getMessage());
                    return;
                }
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (rawData.toString().isEmpty()) {
            System.out.println("Нет данных которые нужно обработать!");
            return;
        }
        Auction auction = new ConsoleAuction(rawData.toString());
        OptimalBidResult bidResult = auction.accumulation().calculation();
        System.out.printf("%d %s\n", bidResult.getQuantity(), bidResult.getPrice());

    }

    private static class Option {

        private final String flag, opt;

        public Option(String flag, String opt) {
            this.flag = flag;
            this.opt = opt;
        }

        public String getFlag() {
            return flag;
        }

        public String getOpt() {
            return opt;
        }
    }
}
