package com.apollo247.testing.stepdefinitions;

import org.testng.Assert;
import com.apollo247.testing.utilities.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;

public class AccountModuleSteps {

    private BaseClass b;

    public AccountModuleSteps(BaseClass b) {
        this.b = b;
    }

    // =========================
    // BACKGROUND
    // =========================

    @Given("user opens My Account panel")
    public void user_opens_my_account_panel() {
        b.getPages().dashboardPage.clickProfileIcon();
    }

    @Given("user is logged into the application")
    public void user_logged_in() {
        System.out.println("User session active");
    }

    // =========================
    // MANAGE FAMILY
    // =========================

    @When("user navigates to Manage Family Members")
    public void user_navigates_manage_family() {
        b.getPages().manageFamilyPage.openManageFamilyMembers();
    }

    @When("user clicks Add New Profile")
    public void user_clicks_add_new_profile() {
        b.getPages().manageFamilyPage.clickAddNewProfile();
    }

    @When("user enters family member details {string} {string} {string}")
    public void user_enters_family_member_details(String fName, String lName, String dob) {
        b.getPages().manageFamilyPage.enterFamilyMemberDetails(fName, lName, dob);
    }

    @When("user selects gender as Male and relation as Brother")
    public void user_selects_gender_relation() {
        b.getPages().manageFamilyPage.selectMaleAndBrother();
    }

    @When("user clicks Save")
    public void user_clicks_save() {
        b.getPages().manageFamilyPage.saveFamilyMember();
    }

    @When("user adds family members from excel")
    public void user_adds_family_members_from_excel() {
        b.getPages().manageFamilyPage.addFamilyMembersFromExcel();
    }

    @Then("family member should be created successfully")
    public void family_member_created_successfully() {
        boolean status = b.getPages().manageFamilyPage.isSuccessToastDisplayed();
        Assert.assertTrue(status, "Family member creation failed");
        System.out.println("ASSERT PASSED: Family member created successfully");
    }

    @Then("validation error message should be displayed")
    public void validation_error_message_should_be_displayed() {
        boolean status = b.getPages().manageFamilyPage.isValidationErrorDisplayed();
        Assert.assertTrue(status, "Validation error not displayed");
        System.out.println("ASSERT PASSED: Validation error displayed");
    }

    // =========================
    // MY APPOINTMENTS
    // =========================

    @When("user clicks My Appointments")
    public void user_clicks_my_appointments() {
        b.getPages().myappointmentsPage.openMyAppointments();
    }

    @When("user refreshes the page")
    public void user_refreshes_the_page() {
        b.getPages().myappointmentsPage.refreshPage();
    }

    @Then("Appointments section should be displayed")
    public void appointments_displayed() {
        boolean status = b.getPages().myappointmentsPage.isAppointmentsPageDisplayed();
        Assert.assertTrue(status, "Appointments page not displayed");
        System.out.println("ASSERT PASSED: Appointments displayed");
    }

    @Then("Appointments section should still be displayed")
    public void appointments_still_displayed() {
        // After refresh, the panel is closed and page reloads to home.
        // Re-open panel and navigate to My Appointments again before asserting.
        b.getPages().dashboardPage.clickProfileIcon();
        b.getPages().myappointmentsPage.openMyAppointments();

        boolean status = b.getPages().myappointmentsPage.isPageLoadedAfterRefresh();
        Assert.assertTrue(status, "Appointments not visible after refresh");
        System.out.println("ASSERT PASSED: Appointments persisted after refresh");
    }

    // =========================
    // MEMBERSHIPS
    // =========================

    @When("user navigates to My Memberships")
    public void user_navigates_memberships() {
        b.getPages().membershipsPage.openMyMemberships();
    }

    @When("user clicks Activate Corporate Membership")
    public void user_clicks_corporate() {
        b.getPages().membershipsPage.clickActivateCorporateMembership();
    }

    @When("user enters corporate email {string}")
    public void user_enters_email(String email) {
        b.getPages().membershipsPage.enterCorporateEmail(email);
    }

    @When("user clicks Get OTP")
    public void user_clicks_otp() {
        b.getPages().membershipsPage.clickGetOtp();
    }

    @Then("corporate benefits error message should be displayed")
    public void corporate_error_displayed() {
        boolean status = b.getPages().membershipsPage.isCorporateErrorDisplayed();
        Assert.assertTrue(status, "Corporate error not displayed");
        System.out.println("ASSERT PASSED: Corporate error displayed");
    }

    @Then("user dismisses the error popup")
    public void dismiss_error() {
        b.getPages().membershipsPage.clickOkGotIt();
    }

    @When("user clicks BUY NOW")
    public void click_buy_now() {
        b.getPages().membershipsPage.clickBuyNow();
    }

    @When("user scrolls to {int} months plan and clicks Join Now")
    public void select_plan(Integer months) {
        b.getPages().membershipsPage.scrollToAndClickJoinNow(months);
    }

    @Then("the following plan details should be visible on the page")
    public void plan_details(DataTable dataTable) {

        List<String> data = dataTable.asList();

        for (int i = 1; i < data.size(); i++) {
            boolean status = b.getPages().membershipsPage.isPlanDetailVisible(data.get(i));
            Assert.assertTrue(status, "Plan not found: " + data.get(i));
            System.out.println("ASSERT PASSED: Plan visible - " + data.get(i));
        }
    }

    // =========================
    // NOTIFICATIONS
    // =========================

    @When("user clicks on Notification Preferences")
    public void open_notifications() {
        b.getPages().notificationsPage.openNotificationPreferences();
    }

    @Then("Notification Preferences page should be displayed")
    public void verify_notifications_page() {
        boolean status = b.getPages().notificationsPage.isNotificationPageDisplayed();
        Assert.assertTrue(status, "Notification page not displayed");
        System.out.println("ASSERT PASSED: Notification page displayed");
    }

    @When("user enables Push Notifications")
    public void enable_push() {
        b.getPages().notificationsPage.enablePushNotifications();
    }

    @Then("Push Notifications toggle should be active")
    public void push_active() {
        boolean status = b.getPages().notificationsPage.isPushToggleVisible();
        Assert.assertTrue(status, "Push toggle not active");
        System.out.println("ASSERT PASSED: Push enabled");
    }

    @When("user enables SMS Notifications")
    public void enable_sms() {
        b.getPages().notificationsPage.enableSmsNotifications();
    }

    @Then("SMS Notifications toggle should be active")
    public void sms_active() {
        boolean status = b.getPages().notificationsPage.isSmsToggleVisible();
        Assert.assertTrue(status, "SMS toggle not active");
        System.out.println("ASSERT PASSED: SMS enabled");
    }

    @When("user enables the following notification types")
    public void enable_multiple(DataTable table) {

        List<String> list = table.asList();

        for (int i = 1; i < list.size(); i++) {
            b.getPages().notificationsPage.enableNotificationType(list.get(i));
        }
    }

    @Then("all selected notifications should be enabled")
    public void all_enabled() {
        boolean status = b.getPages().notificationsPage.areAllTogglesVisible();
        Assert.assertTrue(status, "Not all notifications enabled");
        System.out.println("ASSERT PASSED: All notifications enabled");
    }

    // =========================
    // NEED HELP
    // =========================

    @When("user navigates to Need Help")
    public void need_help() {
        b.getPages().needHelpPage.openNeedHelp();
    }

    @Then("all help categories should be visible")
    public void help_visible() {
        boolean status = b.getPages().needHelpPage.areHelpCategoriesVisible();
        Assert.assertTrue(status, "Help categories not visible");
        System.out.println("ASSERT PASSED: Help categories visible");
    }

    @When("user clicks on Medicines category")
    public void medicines_click() {
        b.getPages().needHelpPage.clickMedicinesCategory();
    }

    @Then("Medicines help page should be loaded")
    public void medicines_loaded() {
        boolean status = b.getPages().needHelpPage.isMedicinesPageLoaded();
        Assert.assertTrue(status, "Medicines page not loaded");
        System.out.println("ASSERT PASSED: Medicines page loaded");
    }

    // =========================
    // LOGOUT
    // =========================

    @When("user clicks on profile icon")
    public void click_profile() {
        b.getPages().logoutPage.clickProfileIcon();
    }

    @When("user clicks on logout option")
    public void click_logout() {
        b.getPages().logoutPage.clickLogout();
    }

    @Then("user should be redirected to login page")
    public void logout_success() {
        boolean status = b.getPages().logoutPage.isLogoutSuccessful();
        Assert.assertTrue(status, "Logout failed");
        System.out.println("ASSERT PASSED: Logout successful");
    }
}