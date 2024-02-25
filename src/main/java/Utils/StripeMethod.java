package Utils;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import Models.CategorieCodePromo;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StripeMethod {
    private static final String STRIPE_SECRET_KEY = "sk_test_51Oj3svCXr5Afrxv4MljO3Ftvb6voQEdB9nctTfpnvuKiy8LvvQGdaP2bQO7LCIhlFhkohFlgTaJtOFFLcWwlJ7bz00d8oFypNE";

    static {
        Stripe.apiKey = STRIPE_SECRET_KEY;  // Replace with your actual Stripe secret key

    }
    public String createCustomerAndGenerateToken() throws StripeException {
        // Use test card details
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 12);
        cardParams.put("exp_year", Year.now().getValue() + 1);
        cardParams.put("cvc", "123");

        Map<String, Object> tokenParams = new HashMap<>();
        tokenParams.put("card", cardParams);

        // Use the test card details to create a token
        Token token = Token.create(tokenParams);

        // Create a customer and attach the test token to the customer
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("email", "john.doe@example.com");
        customerParams.put("source", token.getId());
        Customer customer = Customer.create(customerParams);

        return customer.getId();
    }

    public String generateTestToken() throws StripeException {
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 12);
        cardParams.put("exp_year", 2023);
        cardParams.put("cvc", "123");

        Map<String, Object> tokenParams = new HashMap<>();
        tokenParams.put("card", cardParams);

        Token token = Token.create(tokenParams);

        return token.getId();
    }
/*
        public String createCustomerAndGenerateToken(String cardNumber, String expMonth, String expYear, String cvc)
                throws StripeException {
            // Generate a test token using actual card details
            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", cardNumber);
            cardParams.put("exp_month", expMonth);
            cardParams.put("exp_year", expYear);
            cardParams.put("cvc", cvc);

            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("card", cardParams);

            Token token = Token.create(tokenParams);

            // Create a customer and attach the token to the customer
            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put("email", "john.doe@example.com");
            customerParams.put("source", token.getId());
            Customer customer = Customer.create(customerParams);

            return customer.getId();
        }
        public String createCustomerAndGenerateToken(String cardNumber, String expMonth, String expYear, String cvc)
            throws StripeException {
        // Generate a test token using actual card details
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expMonth);
        cardParams.put("exp_year", expYear);
        cardParams.put("cvc", cvc);

        Map<String, Object> tokenParams = new HashMap<>();
        tokenParams.put("card", cardParams);

        // Use the test card details to create a token
        Token token = Token.create(tokenParams);

        // Create a customer and attach the test token to the customer
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("email", "john.doe@example.com");
        customerParams.put("source", token.getId());
        Customer customer = Customer.create(customerParams);

        return customer.getId();
    }*/

        public void makePayment(int amount, String customerId, CategorieCodePromo codePromo) throws StripeException {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", calculateDiscountedAmount(amount, codePromo));
            params.put("currency", "usd");
            params.put("customer", customerId);
            Charge.create(params);
            System.out.println("Payment successful");
        }

        private int calculateDiscountedAmount(int originalAmount, CategorieCodePromo codePromo) {
            // Apply code promo discount if applicable
            if (codePromo != null && codePromo.getNb_users() > 0) {
                double discount = codePromo.getValue(); // Use double for precision
                return (int) Math.round(originalAmount - (originalAmount * discount));
            }
            return originalAmount;
        }
    }


