Feature: Validate the Countdown Functionality of EggTimer
 
Scenario: Enter time in EggTimer to start countdown in seconds
	Given User is on Home Page of Egg Timer
	When User enters time and starts timer	
    Then Countdown remaining time decreases in seconds

Examples:
|browser|url|time|
|chrome|https://e.ggtimer.com/|25 seconds|

 