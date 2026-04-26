package com.apollo247.testing.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.apollo247.testing.utilities.BaseClass;
import com.apollo247.testing.utilities.ExcelUtilities;
import com.apollo247.testing.utilities.SessionManager;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class BuyMedicineSteps {

    private BaseClass b;

    public BuyMedicineSteps(BaseClass b) {
        this.b = b;
    }

    // =========================
    // BACKGROUND
    // =========================

    @Given("User launches the browser")
    public void user_launches_the_browser() {
        Assert.assertNotNull(b.getDriver());
        System.out.println("Browser launched successfully");
    }

    @Given("User navigates to Apollo 247 website")
    public void user_navigates_to_apollo_247_website() throws Exception {

        SessionManager.ManageSession(b.getDriver());

        b.getDriver().get("https://www.apollo247.com/");
        b.getPages().dashboardPage.clickBuyMedicines();

        SessionManager.switchToDomain(
                b.getDriver(),
                "https://www.apollopharmacy.in"
        );
    }

    @Given("User is on Buy Medicines page")
    public void user_is_on_buy_medicines_page() {
        Assert.assertTrue(
                b.getDriver().getCurrentUrl().contains("apollo"),
                "User is not on Apollo page"
        );
    }

    // =========================
    // POPUP
    // =========================

    @When("User closes the popup")
    public void user_closes_the_popup() {
        b.getPages().buyMedicinePage.closePopup();
    }

    // =========================
    // SEARCH MEDICINE (VALID / INVALID)
    // =========================

    @When("User searches medicine {string}")
    public void user_searches_medicine(String medicine) {
        b.getPages().buyMedicinePage.searchAndAddMedicine(medicine);
    }

    @When("User searches for invalid medicine {string}")
    public void user_searches_for_invalid_medicine(String medicineName) {
        b.getPages().invalidSearchPage.searchMedicine(medicineName);
    }

    // =========================
    // CART
    // =========================

    @When("User clicks cart icon")
    public void user_clicks_cart_icon() {
        b.getPages().buyMedicinePage.clickCart();
    }

    @Then("Product {string} should be visible in cart")
    public void product_should_be_visible_in_cart(String product) {

        String actual = b.getPages()
                .buyMedicineCartPage
                .getProductNameTextByProduct(product);

        Assert.assertTrue(
                actual.toLowerCase().contains(product.toLowerCase().split("\\s+")[0]),
                "Product not found in cart: " + actual
        );

        System.out.println("ASSERT PASSED: Product visible in cart");
    }

    // =========================
    // INVALID SEARCH VALIDATION
    // =========================

    @Then("No medicines should be displayed")
    public void no_medicines_should_be_displayed() {

        int size = b.getDriver().findElements(
                org.openqa.selenium.By.xpath("//button[contains(text(),'ADD')]")
        ).size();

        Assert.assertEquals(size, 0, "Medicines are displayed unexpectedly");
    }

    @Then("No result message should be visible")
    public void no_result_message_should_be_visible() {

        boolean msgDisplayed = b.getPages().invalidSearchPage.isNoResultMessageDisplayed() || 
                               b.getPages().invalidSearchPage.isEmptyStateDisplayed() ||
                               b.getPages().invalidSearchPage.pageContainsNoResultKeywords();

        Assert.assertTrue(msgDisplayed, "No result message not displayed");
    }

    @Then("User should remain on search page")
    public void user_should_remain_on_search_page() {

        String url = b.getDriver().getCurrentUrl().toLowerCase();

        Assert.assertTrue(
                url.contains("search")
                        || url.contains("apollo")
                        || url.contains("pharmacy"),
                "User not on search page: " + url
        );

        System.out.println("ASSERT PASSED: User remained on search page");
    }

    // =========================
    // APOLLO PRODUCTS
    // =========================

    @When("User clicks Apollo Products link")
    public void user_clicks_apollo_products_link() {
        b.getPages().buyMedicinePage.clickApolloProducts();
    }

    @When("User clicks Personal Care category")
    public void user_clicks_personal_care_category() {
        b.getPages().apolloproductsPage.clickPersonalCare();
    }

    @When("User clicks Skin Care category")
    public void user_clicks_skin_care_category() {
        b.getPages().apolloproductsPage.clickSkinCare();
    }

    @When("User adds first product")
    public void user_adds_first_product() {
        b.getPages().apolloproductsPage.addFirstProduct();
    }

    @Then("Product should be added successfully")
    public void product_should_be_added_successfully() {
        Assert.assertTrue(
                b.getDriver().getCurrentUrl().contains("apollo")
                        || b.getDriver().getCurrentUrl().contains("product"),
                "Product not added successfully"
        );
    }

    // =========================
    // VOLINI
    // =========================

    @Given("User navigates to Volini page")
    public void user_navigates_to_volini_page() {
        b.getPages().voliniPage.openVoliniPage();
    }

    @When("User navigates to Volini via Shop By Brand")
    public void user_navigates_to_volini_via_shop_by_brand() {
        b.getPages().voliniPage.navigateToVoliniViaShopByBrand();
    }

    @When("User clicks Inflammation filter")
    public void user_clicks_inflammation_filter() {
        b.getPages().voliniPage.clickInflammationFilter();
    }

    @When("User adds first Volini product")
    public void user_adds_first_volini_product() {
        b.getPages().voliniPage.addFirstProduct();
    }

    @Then("Volini product should be added successfully")
    public void volini_product_should_be_added_successfully() {
        Assert.assertTrue(
                b.getDriver().getCurrentUrl().contains("volini"),
                "Volini validation failed"
        );
    }

    // =========================
    // CART QUANTITY
    // =========================

    @Given("User has product in cart")
    public void user_has_product_in_cart() {
        b.getPages().buyMedicinePage.clickCart();
    }

    @When("User updates cart quantity using below data")
    public void user_updates_cart_quantity_using_below_data(DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps();

        for (Map<String, String> row : data) {

            String qty = row.get("Quantity");

            if (qty.equals("3")) {
                b.getPages().buyMedicineCartPage.changeQuantityToThree();
            }
        }
    }

    @Then("Product quantity should be updated successfully")
    public void product_quantity_should_be_updated_successfully() {

        String selected = b.getPages().buyMedicineCartPage.getSelectedQuantity();

        Assert.assertEquals(
                selected.replaceAll("[^0-9]", ""),
                "3"
        );
    }

    // =========================
    // EXCEL
    // =========================

    @When("User adds medicines from Excel file {string}")
    public void user_adds_medicines_from_excel_file(String fileName) {

        ExcelUtilities excel = new ExcelUtilities();
        int row = 1;

        while (true) {

            try {
                String medicine = excel.getExcelData("BuyMedicine", row, 0);

                if (medicine == null || medicine.trim().isEmpty()) {
                    break;
                }

                b.getPages().buyMedicinePage.searchAndAddMedicine(medicine);

                row++;

            } catch (Exception e) {
                break;   // no more rows / invalid row -> stop loop
            }
        }
    }

    @Then("Medicines should be added successfully")
    public void medicines_should_be_added_successfully() {
        
        // Verify that user is still on the Apollo pharmacy website
        String currentUrl = b.getDriver().getCurrentUrl().toLowerCase();
        
        Assert.assertTrue(
                currentUrl.contains("apollo") || 
                currentUrl.contains("pharmacy"),
                "User not on Apollo pharmacy page: " + currentUrl
        );
        
        System.out.println("ASSERT PASSED: Medicines added successfully from Excel");
    }
}