package co.uk.heartbingo.pages;


import co.uk.heartbingo.utility.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends Utility {

    private static final Logger log = LogManager.getLogger(HomePage.class);

    @CacheLookup
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    WebElement acceptCookiesButton;

    @CacheLookup
    @FindBy(css = ".buttons__login_bar--login.bvs-button")
    WebElement loginText;

    @CacheLookup
    @FindBy(xpath = "//a[normalize-space()='Sign Up']")
    WebElement signupText;

    @CacheLookup
    @FindBy(xpath = "//span[@class='bvs-icon is-menu is-big floating-nav__header-item is-inverted']")
    WebElement headerMenu;

    @CacheLookup
    @FindBy(xpath = "//a[normalize-space()='Log In']")
    WebElement loginLink;

    @CacheLookup
    @FindBy(css = ".oneauth-iframe")
    WebElement iframe;

    @CacheLookup
    @FindBy(id = "username")
    WebElement emailField;

    @CacheLookup
    @FindBy(id = "password")
    WebElement passwordField;

    @CacheLookup
    @FindBy(xpath = "//button[@name='login_submit']")
    WebElement loginButton;

    @CacheLookup
    @FindBy(css = "a[type='site-header-account-deposit']")
    WebElement depositText;

    public void clickOnAcceptCookiesButton() {
        clickOnElement(acceptCookiesButton);
        log.info("Clicking on ---accept cookies--- button : " + acceptCookiesButton.toString());
    }

    public String getTitleOfPage() {
        log.info("Home page title ---" + driver.getTitle() + "--- is visible");
        return driver.getTitle();
    }

    public boolean verifyLoginLinkIsDisplayed() {
        log.info("---Login Link--- is displayed");
        return loginText.isDisplayed();
    }

    public boolean verifySignupLinkIsDisplayed() {
        log.info("---Join Now--- Link is displayed");
        return signupText.isDisplayed();
    }

    public boolean verifyHeaderMenuIsDisplayed() {
        log.info("---Search Button--- is displayed");
        return headerMenu.isDisplayed();
    }

    public String verifyNavigationMenu(String menu) {

        List<WebElement> getMenu = driver.findElements(By.xpath("//div[@class='site-main-nav-wrapper']//li"));
        String value = null;
        for (WebElement g : getMenu) {
            if (menu.equalsIgnoreCase(g.getText())) {
                value = g.getText();
                break; }
            else{ value = null;}}
        if (value!=null){  log.info("Option ---" + value + "--- is visible in navigation menu bar ");
            } else { log.info("Option ---" + menu + "--- is NOT visible in navigation menu bar ");
            }
        return value;
    }
    public void enterValidCredentialsAndLogin(String email,String password){
        log.info("---Logging in--- ");
        clickOnElement(loginLink);
        driver.switchTo().frame(iframe);
        sendTextToElement(emailField,email);
        sendTextToElement(passwordField, password);
        clickOnElement(loginButton);
    }
    public String verifyNavigateToLoginPage(){
        log.info("---Logged in--- ");
        return getTextFromElement(depositText);
    }
}
