package com.company.app;

import com.company.entity.AuctionDirection;
import com.company.entity.AuctionOrder;
import com.company.entity.OptimalBidResult;
import com.company.entity.SummaryOrder;

import java.util.Map;

public abstract class Auction {

    protected static Map<Character, SummaryOrder> orders;


    public abstract Auction accumulation();


    public OptimalBidResult calculation() {
        OptimalBidResult result = new OptimalBidResult();
        SummaryOrder summaryOrderSell = orders.get(AuctionDirection.SELL.getValue());
        SummaryOrder summaryOrderBuy = orders.get(AuctionDirection.BUY.getValue());
        if (summaryOrderSell != null && summaryOrderBuy != null && summaryOrderSell.getOrders().size() > 0 && summaryOrderBuy.getOrders().size() > 0 && summaryOrderSell.getMinPrice() <= summaryOrderBuy.getMaxPrice()) {
            int sum;
            double orderSellAverage = summaryOrderSell.getAverage(summaryOrderBuy.getMaxPrice(), summaryOrderSell.getMinPrice());
            int sumSell = summaryOrderSell.getOrders().stream()
                    .filter(order -> (summaryOrderBuy.getMaxPrice() >= order.getPrice()) && (summaryOrderSell.getMinPrice() <= order.getPrice()))
                    .mapToInt(AuctionOrder::getQuantity).sum();
            int sumBuy = summaryOrderBuy.getOrders().stream()
                    .filter(order -> (summaryOrderBuy.getMaxPrice() >= order.getPrice()) && (summaryOrderSell.getMinPrice() <= order.getPrice()))
                    .mapToInt(AuctionOrder::getQuantity).sum();
            sum = Math.min(sumBuy, sumSell);
            result = new OptimalBidResult(sum, orderSellAverage);
        }
        return result;
    }
}
