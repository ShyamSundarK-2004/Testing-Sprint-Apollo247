Feature: Apollo 247 Health Insurance - Member Selection Validation
	
	  Background:
	    
	    Given User navigates to Health Insurance page and enter pincode "601201"
	
	  #@smoke @insurance @memberSelection
	  #Scenario: Verify valid member selection (Female and Self)
	   # When User selects "Male" and "Self" at the age "22" as members
	 #  And User clicks on "View Plans"
	#Then Insurance plans should be loaded successfully
	    
	  # @negative @insurance @validation
	  #Scenario: Verify validation when no member is selected
	   #When User clicks on "View Plans" without selecting any member
	   #Then Proper validation error message "Select minimum one adult" should be displayed
	   
	   @regression @insurance @memberSelection
	Scenario: Verify multiple member selection (Self, Wife, Father)/ // .
	    When User selects Gender "Female" "Self" at age "28", "Husband" at age "25", and "Father"  at age "55" as members
	    And User clicks on "View Plans"
	    Then Family insurance plans should be displayed correctly