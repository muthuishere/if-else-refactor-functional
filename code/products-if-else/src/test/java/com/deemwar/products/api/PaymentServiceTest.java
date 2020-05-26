package com.deemwar.products.api;

import com.deemwar.products.api.models.CustomerType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PaymentServiceTest {


    PaymentService paymentService = new PaymentService();

    @Test
    public void testCalculateShipping(){
        assertThat(paymentService.calculateShipping(65),equalTo(400L));
        assertThat(paymentService.calculateShipping(15),equalTo(350L));

    }


    @Test
    public void testRetrieveGiftDetails(){
        assertThat(paymentService.retrieveGiftDetails(CustomerType.PLATINUM),equalTo("SOME_GIFT_WORTH_50"));
        assertThat(paymentService.retrieveGiftDetails(CustomerType.GOLD),equalTo("SOME_GIFT_WORTH_30"));
        assertThat(paymentService.retrieveGiftDetails(CustomerType.CLASSIC),equalTo("SOME_GIFT_WORTH_10"));
    }
    @Test
    public void CalculatePriceAndApplyDiscountShouldApplytDiscountAs1PercentForThreeProducts(){

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product Foot", "Footwear",  1500.0));
        products.add(new Product("Product Cloth", "Clothing", 2400.0));
        products.add(new Product("Product Watch", "Watches",  1100.0));

        assertThat(paymentService.calculatePriceAndApplyDiscount(products),equalTo(4950.0));

    }
    @Test
    public void CalculatePriceAndApplyDiscountShouldApplytDiscountAs10PercentForSixProducts(){

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product Foot", "Footwear",  1500.0));
        products.add(new Product("Product Cloth", "Clothing", 2400.0));
        products.add(new Product("Product Watch", "Watches",  1100.0));
        products.add(new Product("Product Foot1", "Footwear",  1500.0));
        products.add(new Product("Product Cloth1", "Clothing", 2400.0));
        products.add(new Product("Product Watch1", "Watches",  1100.0));


        assertThat(paymentService.calculatePriceAndApplyDiscount(products),equalTo(9500.0));

    }
    @Test
    public void CalculatePriceAndApplyDiscountShouldApplytDiscountAs15PercentFor18Products(){

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product Foot", "Footwear",  1500.0));
        products.add(new Product("Product Cloth", "Clothing", 2400.0));
        products.add(new Product("Product Watch", "Watches",  1100.0));
        products.add(new Product("Product Foot1", "Footwear",  1500.0));
        products.add(new Product("Product Cloth2", "Clothing", 2400.0));
        products.add(new Product("Product Watch3", "Watches",  1100.0));
        products.add(new Product("Product Foot4", "Footwear",  1500.0));
        products.add(new Product("Product Cloth5", "Clothing", 2400.0));
        products.add(new Product("Product Watch6", "Watches",  1100.0));
        products.add(new Product("Product Foot7", "Footwear",  1500.0));
        products.add(new Product("Product Cloth8", "Clothing", 2400.0));
        products.add(new Product("Product Watch9", "Watches",  1100.0));
        products.add(new Product("Product Foot10", "Footwear",  1500.0));
        products.add(new Product("Product Cloth11", "Clothing", 2400.0));
        products.add(new Product("Product Watch12", "Watches",  1100.0));
        products.add(new Product("Product Foot13", "Footwear",  1500.0));
        products.add(new Product("Product Cloth14", "Clothing", 2400.0));
        products.add(new Product("Product Watch15", "Watches",  1100.0));


        assertThat(paymentService.calculatePriceAndApplyDiscount(products),equalTo(27000.0));

    }
}