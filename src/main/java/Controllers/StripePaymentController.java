package Controllers;

import Models.CategorieCodePromo;
import Models.Cours;
import Services.CategorieCodePromoService;
import Utils.StripeMethod;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;

public class StripePaymentController {

    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField cvcField;
    @FXML
    private TextField promoCodeField;
    @FXML
    private TextField validCode;
    @FXML
    private TextField expMonthField;
    @FXML
    private TextField expYearField;

    private int courseId;
    private int coursePrice;

    private final CategorieCodePromoService categorieCodePromoService = new CategorieCodePromoService();

    public void setCourseDetails(int courseId, int coursePrice) {
        this.courseId = courseId;
        this.coursePrice = coursePrice;
    }

    @FXML
    void initialize() {
        updateValidCode();
    }
/*
    @FXML
    private void makePayment() {
        try {
            String promoCode = promoCodeField.getText();

            // Fetch the code promo from the service
            CategorieCodePromo appliedCodePromo = categorieCodePromoService.getCategorieByCode(promoCode);

            // Use the fetched code promo for payment
            StripeMethod stripeMethod = new StripeMethod();
            String customerId = stripeMethod.createCustomer("John Doe", "john.doe@example.com");

            // Use a test card number to generate a token
            String token = stripeMethod.generateTestToken();

            // Add card to customer using the generated token
            stripeMethod.addCardToCustomer(customerId, token);

            // Make payment with the calculated discounted amount
            stripeMethod.makePayment(calculateFinalAmount(new Cours(1, 200)), customerId, appliedCodePromo);

            // Update nb_users in CategorieCodePromo table
            updateNbUsers(promoCode);
            updateValidCode();

            showSuccessAlert("Payment Successful", "Payment made successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Payment Error", "An error occurred during payment. Please try again. Error: " + e.getMessage());
        }
    }*/
@FXML
private void makePayment() {
    try {
        String promoCode = promoCodeField.getText();

        // Fetch the code promo from the service
        CategorieCodePromo appliedCodePromo = categorieCodePromoService.getCategorieByCode(promoCode);

        // Use the fetched code promo for payment
        StripeMethod stripeMethod = new StripeMethod();

        // Generate a test token
        String token = stripeMethod.generateTestToken();

        // Make payment with the calculated discounted amount
        stripeMethod.makePayment(calculateFinalAmount(new Cours(1, 200)), token, appliedCodePromo);

        // Update nb_users in CategorieCodePromo table
        updateNbUsers(promoCode);
        updateValidCode();

        showSuccessAlert("Payment Successful", "Payment made successfully!");

    } catch (Exception e) {
        e.printStackTrace();
        showErrorAlert("Payment Error", "An error occurred during payment. Please try again. Error: " + e.getMessage());
    }
}


    @FXML
    void updateValidCode() {
        String validCodeText = fetchValidCodeFromDatabase();
        validCode.setText(validCodeText);
    }

    private String fetchValidCodeFromDatabase() {
        List<CategorieCodePromo> allPromoCodes = categorieCodePromoService.getAllCategories();

        for (CategorieCodePromo promoCode : allPromoCodes) {
            if (promoCode.getNb_users() > 0) {
                return promoCode.getCode();
            }
        }

        return "NoValidCodesAvailable";
    }

    private int calculateFinalAmount(Cours cours) {
        return (int) cours.getPrix();
    }

    private void updateNbUsers(String promoCode) {
        // Retrieve the category for the given promo code
        CategorieCodePromo category = categorieCodePromoService.getCategorieByCode(promoCode);

        // Check if the category exists
        if (category != null && category.getNb_users() > 0) {
            // Decrement nb_users by 1 and update in the database
            category.setNb_users(category.getNb_users() - 1);
            categorieCodePromoService.updateCategorieCodePromo(category);
        }
    }

    private void showSuccessAlert(String title, String content) {
        showAlert(title, content, Alert.AlertType.INFORMATION);
    }

    private void showWarningAlert(String title, String content) {
        showAlert(title, content, Alert.AlertType.WARNING);
    }

    private void showErrorAlert(String title, String content) {
        showAlert(title, content, Alert.AlertType.ERROR);
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
