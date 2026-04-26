     Feature: Apollo 247 Health Insurance - Member Selection Validation
	
	 Background:

	    
	 Given User navigates to Health Insurance page and enter pincode "601201"
	
	 ##############################################--------Scenario 1------------############################################################
	 @smoke @insurance @memberSelection
     Scenario Outline: Verify valid member selection (Female and Self)
     When User selects "<Gender>" and "<member>" at the age "<age>" as members
     And User clicks on "View Plans"
     Then Insurance plans should be loaded successfully
	 Examples:
     | Gender | member  | age |
     | Male   | Self    | 22  |
     | Female | Husband | 43  |
     | Female | Mother  | 55  |
	
	 ##############################################--------Scenario 2------------############################################################  
	 @negative @insurance @validation
	 Scenario: Verify validation when no member is selected
	 When User clicks on "View Plans" without selecting any member
	 Then Proper validation error message "Select minimum one adult" should be displayed
	 
	 ##############################################--------Scenario 3------------############################################################  
	 @regression @insurance @memberSelection
     Scenario: Verify multiple member selection (Self, Husband, Father)
     When User selects members with following details
     | Gender | Member   | Age |
     | Female | Self     | 28  |
     | Female | Husband  | 25  |
     | Female | Father   | 55  |
     And User clicks on "View Plans"
     Then Family insurance plans should be displayed correctly
	 
	 ##############################################--------Scenario 4------------############################################################   
	 @filtering @sorting 
	 Scenario: Verify member selection, plans listing, filter and sorting
     When User selects "Male" and "Self" at the age "22" as members
     And User clicks on "View Plans"
     And User applies coverage filter between "₹10-24 Lakh"
     And User selects room rent type as "Single private room"
     And User sorts plans by "Premium - Low to high"
     Then Plans should be displayed based on applied filters coverage amount "₹10-24 Lakh" and "Premium"
     
     ##############################################--------Scenario 5------------############################################################   
     @policyPurchase @validDetails
     Scenario: Purchase Insurance Plan with valid details
	 When User selects "Male" and "Self" at the age "22" as members
     When User clicks on "View Plans"
     And User clicks proceeds with plan customization
     And  User completes step1 Member Details form 
     And User completes  step2 Medical Questions form 
     And User completes step3 Proposer Details form 
     And  User completes step4 KYC Verification  upload document
     And  User completes step5 address proof  upload document
     And  User completes step6 Address Details 
     And User completes step7 Nomiee Selection 
     And User completes step8 Bank Account Details 
     And User completes all the forms and clicks 
     And User accepts Terms and Conditions
     Then User reviews policy details 
     When User clicks on policy form 
     Then The Payment Options page should be displayed with text and validating the policy
    
    
    
    
    