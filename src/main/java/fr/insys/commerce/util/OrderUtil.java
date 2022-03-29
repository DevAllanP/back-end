package fr.insys.commerce.util;

import java.math.BigDecimal;

import org.javamoney.moneta.Money;

import fr.insys.commerce.dto.LigneCommandeStripeDto;
import fr.insys.commerce.dto.ProduitStripeDto;
import fr.insys.commerce.models.Order;

public class OrderUtil {
    public static Long calculateOrderAmountInCents(Order order) {
        BigDecimal amount = new BigDecimal("0");

        if (order != null && order.getLineItems() != null) {
            for (LigneCommandeStripeDto lineItem : order.getLineItems()) {
                if (lineItem.getQuantite() > 0) {
                    ProduitStripeDto product = lineItem.getProduit();
                    Money productPrice = product.getPrice();
                    Money totalCost = productPrice.multiply(lineItem.getQuantite());
                    amount = amount.add(totalCost.getNumberStripped());
                }
            };
        }
        
        amount = amount.multiply(new BigDecimal("100"));
        
        return amount.longValue();
    }
}
