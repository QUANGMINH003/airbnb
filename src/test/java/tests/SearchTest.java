package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.RegisterForm;
import utils.ExtentTestManager;
import utils.TestConfig;

@Listeners(ExtentTestNGListener.class)
public class SearchTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private HomePage homePage;

    @BeforeClass
    public void setupClass() {
        playwright = Playwright.create();
        browser = TestConfig.getBrowserType(playwright).launch(TestConfig.getBrowserLaunchOptions());
    }

    @BeforeMethod
    public void setupTest() {
        browserContext = browser.newContext(TestConfig.getNewContextOptions());
        page = browserContext.newPage();
        homePage = new HomePage(page);
    }

    @AfterClass
    public void tearDownClass() {
        if(browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Test
    public void tc07_SearchCardbyPlace() {
        homePage.navigateToWebsite();

        homePage.clickPlaceCardHCM();

        Assert.assertTrue(homePage.isSearchSuccessbyPlace());
    }

    @Test
    public void tc08_SearchbyDay() {
        ExtentTestManager.info("Truy cap website");
        homePage.navigateToWebsite();
        ExtentTestManager.info("Mo table chon ngay checkin va checkout");
        homePage.clickDatePicker();
        ExtentTestManager.info("Chon ngay checkin va checkout");
        homePage.chooseDateBooking(TestConfig.getCheckinDate(), TestConfig.getCheckoutDate());
        ExtentTestManager.info("Thuc hien tim kiem");
        homePage.clickSearchButton();
        page.waitForTimeout(5000);
        Assert.assertTrue(homePage.isSearchSuccess());
    }

    @Test
    public void tc09_SearchByQuantityGuest() {
        ExtentTestManager.info("Truy cap website");
        homePage.navigateToWebsite();
        ExtentTestManager.info("Them so luong khac");
        homePage.AddQuantityGuest();
        ExtentTestManager.info("Thuc hien tim kiem");
        homePage.clickSearchButton();
        page.waitForTimeout(5000);

        Assert.assertTrue(homePage.isSearchSuccess());
    }

}
