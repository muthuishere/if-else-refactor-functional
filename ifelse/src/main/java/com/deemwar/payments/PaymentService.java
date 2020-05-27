package com.deemwar.payments;

import com.deemwar.model.CustomerType;
import com.deemwar.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class PaymentService {

    //Based on single condition
    public Long calculateShipping(Integer distanceInMiles){
        if(distanceInMiles < 50)
            return 350L;
        else
            return 400L;
    }

    //Based on Equals Condition & Static Result
    public String retrieveGiftDetails(CustomerType customerType){

        if(customerType.equals(CustomerType.PLATINUM))
            return "SOME_GIFT_WORTH_50";
        else if(customerType.equals(CustomerType.GOLD))
            return "SOME_GIFT_WORTH_30";
        else
            return "SOME_GIFT_WORTH_10";




    }


    //Based on rules  & Calculate Price and apply discount
    public Double calculatePriceAndApplyDiscount(List<Product> products){

        Double priceOfProducts =  calculatePrice(products);
        int totalProducts = products.size();

        //if number of products greater than 15 apply 10% discount
        if(totalProducts > 15 ) {
            double tenPercent = priceOfProducts * (10.0 / 100);
            return priceOfProducts - tenPercent;
        } else if(totalProducts > 5 && totalProducts <15)
            //if number of products greater than 5 apply 5% discount overall
        {
            double fivePercent = priceOfProducts * (5.0 / 100);
            return priceOfProducts - fivePercent;
        } else
            //else apply 1 % discount
        {
            double onePercent = priceOfProducts * (1.0 / 100);
            return priceOfProducts - onePercent;
        }


    }

    public Double calculatePrice(List<Product> products){

     return products.stream()
             .mapToDouble(Product::getPrice)
             .sum();
    }




}
