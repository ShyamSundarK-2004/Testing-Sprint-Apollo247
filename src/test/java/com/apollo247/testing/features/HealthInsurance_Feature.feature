Feature: Apollo 247 Health Insurance - Member Selection Validation
	
	  Background:
	    
	    Given User navigates to Health Insurance page and enter pincode "601201"
	
	 # @smoke @insurance @memberSelection
	  #Scenario: Verify valid member selection (Female and Self)
	  #  When User selects "Male" and "Self" at the age "22" as members
	  #And User clicks on "View Plans"
	#Then Insurance plans should be loaded successfully
	    
	  # @negative @insurance @validation
	 # Scenario: Verify validation when no member is selected
	 #  When User clicks on "View Plans" without selecting any member
	 #  Then Proper validation error message "Select minimum one adult" should be displayed
	   
	   @regression @insurance @memberSelection
	#Scenario: Verify multiple member selection (Self, Wife, Father)/ // .
	    #When User selects Gender "Female" "Self" at age "28", "Husband" at age "25", and "Father"  at age "55" as members
	    #And User clicks on "View Plans"
	    #Then Family insurance plans should be displayed correctly
	    
	   @filtering @sorting 
	#Scenario: Verify member selection, plans listing, filter and sorting
   # When User selects "Male" and "Self" at the age "22" as members
   # And User clicks on "View Plans"
    #And User applies coverage filter between "₹10-24 Lakh"
   # And User selects room rent type as "Single private room"
    #And User sorts plans by "Premium - Low to high"
    #Then Plans should be displayed based on applied filters coverage amount "₹10-24 Lakh" and "Premium"
    
    @policyPurchase @validDetails
    Scenario: Purchase Insurance Plan with valid details

	When User selects "Male" and "Self" at the age "22" as members
    When User clicks on "View Plans"
    And User clicks proceeds with plan customization
    And  User completes step1 Member Details form "sg" "anand" "April 15, 2004" "5 ft" "5 in" "78"
    And User completes  step2 Medical Questions form "No" "No" "No" "No" "No"
    And User completes step3 Proposer Details form "sganand@gmail.com" "7402025339" "7402025339" "SM" "AFZPK7190K" 
    And  User completes step4 KYC Verification "Aadhar Card" "273794621727" upload document
    And  User completes step5 address proof "Aadhar Card" "273794621727" upload document
    And  User completes step6 Address Details "No 8 xyz" "gpd" "601201"
    And User completes step7 Nomiee Selection "Neil" "Jun" "Wife" "April 15, 2026" "Jack" "Haltman" "April 10, 2008" "Brother"
    And User completes step8 Bank Account Details "50200027074321" "Savings" "HDFC0000001" 
    And User completes all the forms and clicks "Next"
    And User accepts Terms and Conditions
    Then User reviews policy details
    When User clicks on policy form "Buy Now"
    Then A popup should be displayed with message "Unable to process your request. Please try again."
    And User should click "Go to Homepage" button and validate the policy