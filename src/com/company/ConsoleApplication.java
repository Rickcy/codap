package com.company;

import com.company.app.Auction;
import com.company.app.console.ConsoleAuction;
import com.company.entity.OptimalBidResult;
import com.company.validator.ConsoleValidator;

import java.util.Scanner;

public class ConsoleApplication {


//    ввод через консоль
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder rawData = new StringBuilder();
        ConsoleValidator validator = new ConsoleValidator();
        String currentLine;
        int countLine = 0;
        while (!(currentLine = in.nextLine()).equals("")) {
            if (countLine >= ConsoleValidator.LIMIT_LINES) {
                System.out.printf("\nПривышен лимит данных! (лимит %d)", ConsoleValidator.LIMIT_LINES);
            } else if (validator.validate(currentLine)) {
                rawData.append(currentLine);
                rawData.append(";");
                countLine++;
            } else {
                System.out.println("\nНекоректно введенные входные данные:");
                System.out.println(validator.getMessage());
            }

        }
        in.close();
        if(rawData.toString().isEmpty()) {
            System.out.println("Нет данных которые нужно обработать!");
            return;
        }
        Auction auction = new ConsoleAuction(rawData.toString());
        OptimalBidResult bidResult = auction.accumulation().calculation();
        System.out.printf("%d %s\n", bidResult.getQuantity(), bidResult.getPrice());
    }
}
