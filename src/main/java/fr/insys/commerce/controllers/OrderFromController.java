package fr.insys.commerce.controllers;

import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import fr.insys.commerce.models.CreatePaymentResponse;
import fr.insys.commerce.models.Order;
import fr.insys.commerce.service.OrderFromService;
import fr.insys.commerce.util.CurrencyUtil;
import fr.insys.commerce.util.OrderUtil;

@RestController
@RequestMapping("/paiement")
public class OrderFromController {

	@Autowired
	private OrderFromService orderFromService;
	
	 @Value("${stripe.secret.key}") 
	 private String stripeSecretKey;
	 
	 
	    @GetMapping("/orderForm")
	    public ResponseEntity<Order> orderForm(Model model) {
	    return ResponseEntity.ok().body(orderFromService.getEmptyOrder());
	    }
	    
	    
	    @PostMapping("/orderForm")
	    public ResponseEntity<String> processOrder(Order order, Model model) {
	        Stripe.apiKey = stripeSecretKey;

	        Long totalAmount = OrderUtil.calculateOrderAmountInCents(order);
	        model.addAttribute("totalAmount", Money.of(totalAmount, CurrencyUtil.EUR).divide(100).getNumberStripped());
	        
	        try {
	            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
	                    .setCurrency("eur")
	                    .setAmount(totalAmount)
	                    .build();
	            
	            PaymentIntent intent = PaymentIntent.create(createParams);
	            CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());

	            model.addAttribute("paymentResponse", paymentResponse);
	        } catch (StripeException se) {
	            se.printStackTrace();
	        }
	        
	        return ResponseEntity.ok().body("payement");
	    }
}
