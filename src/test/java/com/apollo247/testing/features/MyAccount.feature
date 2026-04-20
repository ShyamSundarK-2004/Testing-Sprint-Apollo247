Feature: Account Module - All Functionalities

@ManageFamily @Positive
Scenario: Add valid family member from Excel
Given user opens My Account panel
When user navigates to Manage Family Members
And user adds family members from excel
Then family member should be created successfully

@ManageFamily @Negative
Scenario: Add family member with empty details
  Given user opens My Account panel
  When user navigates to Manage Family Members
  And user clicks Add New Profile
  And user clicks Save
  Then validation error message should be displayed

@MyAppointments @AppointmentDisplay
Scenario: Verify My Appointments page loads successfully
Given user opens My Account panel
When user clicks My Appointments
Then Appointments section should be displayed

@MyAppointments @PageReload
Scenario: Verify My Appointments page persists after refresh
Given user opens My Account panel
When user clicks My Appointments
And user refreshes the page
Then Appointments section should still be displayed

@MyMemberships @CorporateEmail
Scenario Outline: Verify error for invalid corporate email
Given user opens My Account panel
When user navigates to My Memberships
And user clicks Activate Corporate Membership
And user enters corporate email "<Email>"
And user clicks Get OTP
Then corporate benefits error message should be displayed
And user dismisses the error popup

Examples:
| Email              |
| [abc@capgemini.com](mailto:abc@capgemini.com)  |
| [test@benz.com](mailto:test@benz.com)      |

@MyMemberships @PlanValidation
Scenario: Verify membership plan details after clicking Buy Now
Given user opens My Account panel
When user navigates to My Memberships
And user clicks BUY NOW
And user scrolls to 12 months plan and clicks Join Now
Then the following plan details should be visible on the page
| planDetail |
| 12 Months  |
| 199        |

@Notifications @NavigationCheck
Scenario: Verify Notification Preferences page loads successfully
Given user opens My Account panel
When user navigates to Manage Family Members
And user clicks on Notification Preferences
Then Notification Preferences page should be displayed

@Notifications @PushNotification
Scenario: Enable Push Notifications toggle successfully
Given user opens My Account panel
When user navigates to Manage Family Members
And user clicks on Notification Preferences
And user enables Push Notifications
Then Push Notifications toggle should be active

@Notifications @SmsNotification
Scenario: Enable SMS Notifications toggle successfully
Given user opens My Account panel
When user navigates to Manage Family Members
And user clicks on Notification Preferences
And user enables SMS Notifications
Then SMS Notifications toggle should be active

@Notifications @AllNotifications
Scenario: Enable all notification types from the preferences page
Given user opens My Account panel
When user navigates to Manage Family Members
And user clicks on Notification Preferences
And user enables the following notification types
| notificationType   |
| Push Notifications |
| SMS Notifications  |
Then all selected notifications should be enabled

@NeedHelp @Navigation
Scenario: Verify Need Help page categories are displayed
Given user opens My Account panel
When user navigates to Need Help
Then all help categories should be visible

@NeedHelp @Medicines
Scenario: Verify user can navigate to Medicines help section
Given user opens My Account panel
When user navigates to Need Help
And user clicks on Medicines category
Then Medicines help page should be loaded

@Logout @Navigation
Scenario: Verify user can successfully logout from the application
Given user is logged into the application
When user clicks on profile icon
And user clicks on logout option
Then user should be redirected to login page
