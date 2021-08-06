package com.company.app.console;

import com.company.app.Auction;
import com.company.entity.AuctionDirection;
import com.company.entity.AuctionOrder;
import com.company.entity.SummaryOrder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class ConsoleAuction extends Auction {

    private final String rawData;

    public ConsoleAuction(String rawData) {
        this.rawData = rawData;
    }

    @Override
    public Auction accumulation() {
        Map<Character, List<AuctionOrder>> ords = Arrays.stream(rawData.split(";"))
                .map(orderRaw -> {
                    String[] parameters = orderRaw.split(" ");
                    return new AuctionOrder(AuctionDirection.create(parameters[0].charAt(0)), parameters[1], parameters[2]);
                }).collect(Collectors.groupingBy(AuctionOrder::getDirection));

        orders = ords.keySet().stream().map(character -> {
            DoubleStream priceStream = ords.get(character).stream().mapToDouble(AuctionOrder::getPrice);
            double priceMax = ords.get(character).stream().mapToDouble(AuctionOrder::getPrice).max().orElse(0);
            double priceMin = ords.get(character).stream().mapToDouble(AuctionOrder::getPrice).min().orElse(0);
            int quantity = ords.get(character).stream().mapToInt(AuctionOrder::getQuantity).sum();
            return new SummaryOrder(AuctionDirection.create(character), quantity, priceMin, priceMax, ords.get(character).stream().sorted(Comparator.comparing(AuctionOrder::getPrice)).collect(Collectors.toList()));
        }).collect(Collectors.toMap(SummaryOrder::getDirection, Function.identity()));

        return this;
    }


}
