package com.deemwar.products.api;

import com.deemwar.products.api.models.CustomerType;
import com.deemwar.products.api.models.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
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


    //Based on rules  & Calculated Result
    public Double calculatePriceAndApplyDiscount(List<Product> products){

        Double priceOfProducts =  calculatePrice(products);


        int totalProducts = products.size();
        //if number of products greater than 5 apply 5% discount overall
        if(totalProducts > 5 && totalProducts <15)
            return priceOfProducts - (priceOfProducts * (5.0/100));
        else  if(totalProducts > 15 )
            return priceOfProducts - (priceOfProducts * (10.0/100));
        else
            return priceOfProducts - (priceOfProducts * (1.0/100));

        //if number of products greater than 15 apply 10% discount

        //else apply 1 % discount
    }

    public Double calculatePrice(List<Product> products){

     return products.stream()
             .mapToDouble(Product::getPrice)
             .sum();
    }




}
