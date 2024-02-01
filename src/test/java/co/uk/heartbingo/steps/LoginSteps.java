package co.uk.heartbingo.steps;


import co.uk.heartbingo.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class LoginSteps {

    @Given("I am on Home Page")
    public void iAmOnHomePage() {
        new HomePage().clickOnAcceptCookiesButton();
    }

    @Then("I verify that the homepage is visible")
    public void iVerifyThatTheHomepageIsVisible() {
        Assert.assertEquals(new HomePage().getTitleOfPage(), "Play Online Bingo | Best UK Bingo Games Site | Heart Bingo", "Homepage is not visible");
    }

    @And("I verify that Login link is displayed")
    public void iVerifyThatLoginLinkIsDisplayed() {
        Assert.assertTrue(new HomePage().verifyLoginLinkIsDisplayed(), "Login Link is not displated");
    }

    @And("I verify that Signup link is displayed")
    public void iVerifyThatSignupLinkIsDisplayed() {
         Assert.assertTrue(new HomePage().verifySignupLinkIsDisplayed(), "Signup Link is not displated");
    }


    @Then("I verify that Header Menu is displayed")
    public void iVerifyThatHeaderMenuIsDisplayed() {
         Assert.assertTrue(new HomePage().verifyHeaderMenuIsDisplayed(), "Header Menu is not displayed");
    }


    @And("I should see below navigation menu options")
    public void iShouldSeeBelowNavigationMenuOptions(DataTable menu) {
        SoftAssert softAssert = new SoftAssert();
        for (Map<String, String> searchMenu : menu.asMaps(String.class, String.class)){
            //Assert.assertEquals(new HomePage().verifyNavigationMenu(searchMenu.get("navigation menu")) , searchMenu.get("navigation menu"), "Not all menu options are available");
            softAssert.assertEquals(new HomePage().verifyNavigationMenu(searchMenu.get("navigation menu")),searchMenu.get("navigation menu"),"Title is not Matching");
        }
    }

    @When("I enter valid email {string} and password {string}")
    public void iEnterValidEmailAndPassword(String email, String password) {
        new HomePage().enterValidCredentialsAndLogin(email,password);
    }

    @Then("I should log in to the account")
    public void iShouldLogInToTheAccount() {
        Assert.assertEquals( new HomePage().verifyNavigateToLoginPage(),"Deposit", "Login Page not available");
    }
}

