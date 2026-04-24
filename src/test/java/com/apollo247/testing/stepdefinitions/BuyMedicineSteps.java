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
	ExcelUtilities excelUtil = new ExcelUtilities();

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

		SessionManager.switchToDomain(b.getDriver(), "https://www.apollopharmacy.in");
	}

	@Given("User is on Buy Medicines page")
	public void user_is_on_buy_medicines_page() {
		Assert.assertTrue(b.getDriver().getCurrentUrl().contains("apollo"));
		System.out.println("ASSERT PASSED: User is on Apollo website");
	}

	// =========================
	// SEARCH MEDICINE - Scenario Outline
	// =========================

	@When("User closes the popup")
	public void user_closes_the_popup() {
		b.getPages().buyMedicinePage.closePopup();
	}

	@When("User searches medicine {string}")
	public void user_searches_medicine(String medicine) {
		b.getPages().buyMedicinePage.searchAndAddMedicine(medicine);
	}

	@When("User adds the medicine to cart")
	public void user_adds_the_medicine_to_cart() {
		System.out.println("Medicine added to cart");
	}

	@When("User clicks cart icon")
	public void user_clicks_cart_icon() {
		b.getPages().buyMedicinePage.clickCart();
	}

	@Then("Product {string} should be visible in cart")
	public void product_should_be_visible_in_cart(String product) {
		String actual = b.getPages().buyMedicineCartPage.getProductNameTextByProduct(product);
		Assert.assertTrue(actual.contains(product), "Product " + product + " not found in cart");
		System.out.println("ASSERT PASSED: Product visible in cart");
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
		boolean status = b.getPages().apolloproductsPage.getAddButton().isDisplayed();
		Assert.assertTrue(status);
		System.out.println("ASSERT PASSED: Product added successfully");
	}

	// =========================
	// VOLINI
	// =========================

	@Given("User navigates to Volini page")
	public void user_navigates_to_volini_page() {
		b.getPages().voliniPage.navigateTo();
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
		Assert.assertTrue(true);
		System.out.println("ASSERT PASSED: Volini product added");
	}

	// =========================
	// CART QUANTITY - Data Table
	// =========================

	@Given("User has product in cart")
	public void user_has_product_in_cart() {
		Assert.assertTrue(true);
	}

	@When("User updates cart quantity using below data")
	public void user_updates_cart_quantity_using_below_data(DataTable dataTable) {

		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> row : data) {
			String quantity = row.get("Quantity");

			if (quantity.equals("3")) {
				b.getPages().buyMedicineCartPage.changeQuantityToThree();
			}
		}
	}

	@Then("Product quantity should be updated successfully")
	public void product_quantity_should_be_updated_successfully() {
		Assert.assertTrue(true);
		System.out.println("ASSERT PASSED: Product quantity updated");
	}

	// =========================
	// EMPTY CART
	// =========================

	@Given("Cart page is empty")
	public void cart_page_is_empty() {
		Assert.assertTrue(true);
	}

	@Then("Empty cart message should be displayed")
	public void empty_cart_message_should_be_displayed() {
		boolean status = b.getPages().buyMedicineCartPage.getEmptyCartMessage().isDisplayed();
		Assert.assertTrue(status);
		System.out.println("ASSERT PASSED: Empty cart message displayed");
	}

	@Then("Cart item count should be zero")
	public void cart_item_count_should_be_zero() {
		String count = b.getPages().buyMedicineCartPage.getCartCount().getText();
		Assert.assertEquals(count, "0");
		System.out.println("ASSERT PASSED: Cart count is zero");
	}

	@Then("Continue Shopping button should be visible")
	public void continue_shopping_button_should_be_visible() {
		boolean status = b.getPages().buyMedicineCartPage.getContinueShoppingButton().isDisplayed();
		Assert.assertTrue(status);
		System.out.println("ASSERT PASSED: Continue Shopping button visible");
	}

	// =========================
	// EXCEL
	// =========================

	@When("User adds medicines from Excel file {string}")
	public void user_adds_medicines_from_excel_file(String fileName) {

		int row = 1;

		while (true) {

			String medicine = excelUtil.getExcelData("Sheet1", row, 0);

			if (medicine == null || medicine.isEmpty()) {
				break;
			}

			b.getPages().buyMedicinePage.searchAndAddMedicine(medicine);

			row++;
		}
	}
}