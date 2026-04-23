package com.apollo247.testing.stepdefinitions;

import org.testng.Assert;
import com.apollo247.testing.utilities.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

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

    @Then("user is logged into the application")
    public void user_logged_in() {
        System.out.println("User assumed logged in");
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

    @Then("family member should be created successfully {string}")
    public void family_member_created_successfully(String expectedMsg) {
        boolean status = b.getPages().manageFamilyPage.isFamilyMemberCreatedSuccessfully();
        Assert.assertEquals(status, true, "Family member creation failed");
    }

    @Then("validation error message should be displayed")
    public void validation_error_message_should_be_displayed() {
        b.getPages().manageFamilyPage.triggerFirstNameValidation();
        
        boolean actual = b.getPages().manageFamilyPage.isFirstNameErrorDisplayed();
        Assert.assertEquals(actual, true, "First name validation error not displayed");
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
        String actual = b.getPages().myappointmentsPage.getAppointmentsHeadingText();
        Assert.assertEquals(actual, "My Appointments", "Appointments page heading not displayed");
    }

    @Then("Appointments section should still be displayed")
    public void appointments_still_displayed() {
        String actual = b.getPages().myappointmentsPage.getAppointmentsHeadingText();
        Assert.assertEquals(actual, "My Appointments", "Appointments page heading not displayed after refresh");
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
        boolean actual = b.getPages().membershipsPage.isCorporateErrorTextCorrect();
        Assert.assertEquals(actual, true, "Corporate error message not displayed correctly");
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
        boolean actual = b.getPages().membershipsPage.validatePlanDetails(dataTable.asList());
        Assert.assertEquals(actual, true, "Plan details validation failed");
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
        boolean actual = b.getPages().notificationsPage.isNotificationPageDisplayed();
        Assert.assertEquals(actual, true, "Notification page not displayed");
    }

    @When("user enables Push Notifications")
    public void enable_push() {
        b.getPages().notificationsPage.enablePushNotifications();
    }

    @Then("Push Notifications toggle should be active")
    public void push_active() {
        boolean actual = b.getPages().notificationsPage.isPushNotificationEnabled();
        Assert.assertEquals(actual, true, "Push toggle not enabled");
    }

    @When("user enables SMS Notifications")
    public void enable_sms() {
        b.getPages().notificationsPage.enableSmsNotifications();
    }

    @Then("SMS Notifications toggle should be active")
    public void sms_active() {
        boolean actual = b.getPages().notificationsPage.isSmsNotificationEnabled();
        Assert.assertEquals(actual, true, "SMS toggle not enabled");
    }

    @When("user enables the following notification types")
    public void enable_multiple(DataTable table) {
        b.getPages().notificationsPage.enableMultipleNotifications(table);
    }

    @Then("all selected notifications should be enabled")
    public void all_enabled() {
        boolean push = b.getPages().notificationsPage.isPushNotificationEnabled();
        boolean sms = b.getPages().notificationsPage.isSmsNotificationEnabled();
        Assert.assertEquals(push && sms, true, "Not all notifications enabled");
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
        boolean actual = b.getPages().needHelpPage.areHelpCategoriesVisible();
        Assert.assertEquals(actual, true, "Help categories not visible");
    }
    
    @When("user clicks on Medicines category")
    public void medicines_click() {
        b.getPages().needHelpPage.clickMedicinesCategory();
    }

    @Then("Medicines help page should be loaded")
    public void medicines_loaded() {
        boolean actual = b.getPages().needHelpPage.isMedicinesPageLoaded();
        Assert.assertEquals(actual, true, "Medicines page not loaded");
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
        Assert.assertEquals(status, true, "Logout failed: Login page is not displayed");
    }
}