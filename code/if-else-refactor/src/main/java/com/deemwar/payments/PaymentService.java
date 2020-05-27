package com.deemwar.payments;

import com.deemwar.model.CustomerType;
import com.deemwar.model.DiscountType;
import com.deemwar.model.Rule;
import com.deemwar.model.Product;
import lombok.extern.slf4j.Slf4j;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;


@Slf4j
public class PaymentService {






    //Based on single condition
    public Long calculateShipping(Integer distanceInMiles){

//        if(distanceInMiles < 50)
//            return 350L;
//        else
//            return 400L;

        return distanceInMiles < 50 ?350L:400L;
    }

    //Based on Equals Condition & Static Result
    public String retrieveGiftDetails(CustomerType customerType){

//        if(customerType.equals(CustomerType.PLATINUM))
//            return "SOME_GIFT_WORTH_50";
//        else if(customerType.equals(CustomerType.GOLD))
//            return "SOME_GIFT_WORTH_30";
//        else
//            return "SOME_GIFT_WORTH_10";

        Map<CustomerType,String> responses = new HashMap<>();
        responses.put(CustomerType.PLATINUM,"SOME_GIFT_WORTH_50");
        responses.put(CustomerType.GOLD,"SOME_GIFT_WORTH_30");

        return responses.containsKey(customerType)?responses.get(customerType):"SOME_GIFT_WORTH_10";



    }


    //Based on rules  & Calculated Result
    public Double calculatePriceAndApplyDiscount(List<Product> products){

        Double priceOfProducts =  calculatePrice(products);
        int totalProducts = products.size();

//
//        //if number of products greater than 15 apply 10% discount
//        if(totalProducts > 15 )
//            return priceOfProducts - (priceOfProducts * (10.0/100));
//        else if(totalProducts > 5 && totalProducts <15)
//            //if number of products greater than 5 apply 5% discount overall
//            return priceOfProducts - (priceOfProducts * (5.0/100));
//        else
//            //else apply 1 % discount
//            return priceOfProducts - (priceOfProducts * (1.0/100));


        Map<DiscountType, Rule<Double>> rules = createRules(priceOfProducts,totalProducts);

        return Stream.of(DiscountType.values())
                .filter(discountType -> rules.get(discountType).isApplicable())
                .map(discountType -> rules.get(discountType).applyProcess())
                .findFirst()
                .orElse(null);

    }

    private Map<DiscountType, Rule<Double>> createRules(Double priceOfProducts, int totalProducts) {
        Map<DiscountType,Rule<Double>> rules = new HashMap<>();
        rules.put(DiscountType.FIFTEEN_PERCENT, createRuleForFifteenPercent(priceOfProducts,totalProducts));
        rules.put(DiscountType.FIVE_PERCENT,createRuleForFivePercent(priceOfProducts,totalProducts));
        rules.put(DiscountType.ONE_PERCENT, createRuleForOnePercent(priceOfProducts,totalProducts));
        return rules;
    }

    Rule<Double> createRuleForFifteenPercent(Double priceOfProducts, int totalProducts) {
        return createRule(
                ()->totalProducts > 15,
                ()->(priceOfProducts - (priceOfProducts * (10.0/100)))
        );
    }

    Rule<Double> createRuleForFivePercent(Double priceOfProducts, int totalProducts) {
        return createRule(
                ()->(totalProducts > 5 && totalProducts <15),
                ()->(priceOfProducts - (priceOfProducts * (5.0/100)))
        );
    }

    Rule<Double> createRuleForOnePercent(Double priceOfProducts, int totalProducts) {
        return createRule(
                ()->(totalProducts <=5),
                ()->(priceOfProducts - (priceOfProducts * (1.0/100)))
        );
    }


    public  Rule<Double> createRule(Supplier<Boolean> rule, Supplier<Double> supplier) {
        return new Rule<>(rule,supplier);
    }

    public Double calculatePrice(List<Product> products){

     return products.stream()
             .mapToDouble(Product::getPrice)
             .sum();
    }




}
