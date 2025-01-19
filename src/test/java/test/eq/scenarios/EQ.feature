Feature: EQ

@SmokeTest
Scenario Outline: Breaker guesses a word
  Given the Maker has chosen a word
  | url |
  | https://opensource-demo.orangehrmlive.com/web/index.php/auth/login |
  When the Breaker makes a guess
  Then the Maker is asked to score "<uname>" and "<password>"
  Examples:
    | uname | password |
    | Admin | admin123 |