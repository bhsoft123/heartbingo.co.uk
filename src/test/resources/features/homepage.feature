@author_Bhumika @regression
Feature:Homepage functionality

  As a user, I should be able to see Home page of HeartBingo and verify visibility of different menu options

@smoke @regression
Scenario:User should be able to see Home Page of Virgin Games and verify links visible
  Given I am on Home Page
  Then  I verify that the homepage is visible
  And   I verify that Login link is displayed
  And   I verify that Signup link is displayed
  Then  I verify that Header Menu is displayed

@sanity @regression
Scenario: User should be able to see all navigation menu options
  Given I am on Home Page
  Then  I verify that the homepage is visible
  And   I should see below navigation menu options
    |navigation menu   |
    |Bingo             |
    |Slots             |
    |Live Casino       |
    |Slingo1           |
    |Slingo            |
    |Promos            |

@sanity @regression
Scenario Outline: User should be able to see all navigation menu options
  Given I am on Home Page
  Then  I verify that the homepage is visible
  When  I enter valid email "<email>" and password "<password>"
  Then  I should log in to the account
  Examples:
    |email              |password     |
    |Abcdxyz@gmail.com  |Abcd@1234    |
