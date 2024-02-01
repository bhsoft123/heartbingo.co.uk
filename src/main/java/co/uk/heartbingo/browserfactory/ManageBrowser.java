package co.uk.heartbingo.browserfactory;


import co.uk.heartbingo.propertyreader.PropertyReader;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ManageBrowser {

        public static WebDriver driver;
        static String baseUrl = PropertyReader.getInstance().getProperty("baseUrl");
        static int implicitlyWait = Integer.parseInt(PropertyReader.getInstance().getProperty("implicitlyWait"));
        public String runOn = PropertyReader.getInstance().getProperty("runOn");
        public String gridUrl = PropertyReader.getInstance().getProperty("gridUrl");

        public ManageBrowser() {
            PageFactory.initElements(driver, this);
            PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/propertiesfile/log4j2.properties");
        }

        public void selectBrowser(String browser)  {
            if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions browserOptions = new ChromeOptions();
//            browserOptions.addExtensions(new File("./Extensions/SelectorsHub.crx"),
//                    new File("./Extensions/Adblock-Plus-free-ad-blocker.crx"));

            if (runOn.equalsIgnoreCase("grid")) {
                System.out.println("##########TEST RUNNING ON GRID ==> On the Browser : " + browser);
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), browserOptions);
                } catch (MalformedURLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("########TESTRUNNING ON LOCAL ===> On the browser : " + browser);
                driver = new ChromeDriver(browserOptions);
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
        FirefoxOptions browserOptions = new FirefoxOptions();
        if (runOn.equalsIgnoreCase("grid")) {
            System.out.println("#########TEST RUNNING ON GRID ==> On the Browser is " + browser);
            try {
                driver = new RemoteWebDriver(new URL(gridUrl), browserOptions);
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("#########TEST RUNNING ON LOCAL ==> On the Browser is " + browser);
            driver = new FirefoxDriver(browserOptions);
        }
    } else if (browser.equalsIgnoreCase("edge")) {
        EdgeOptions browserOptions = new EdgeOptions();
        if (runOn.equalsIgnoreCase("grid")) {
            System.out.println("#########TEST RUNNING ON GRID ==> On the Browser is " + browser);
            try {
                driver = new RemoteWebDriver(new URL(gridUrl), browserOptions);
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("#########TEST RUNNING ON LOCAL ==> On the Browser is " + browser);
            driver = new EdgeDriver(browserOptions);
        }
    } else {
        System.out.println("Wrong browser name");
    }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
            driver.get(baseUrl);

        }

        public void closeBrowser(){
            if (driver!= null){
                driver.quit();
            }
        }
    }


