package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginForm;
import pages.RegisterForm;
import utils.ExtentTestManager;
import utils.TestConfig;


@Listeners(ExtentTestNGListener.class)
public class LoginTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private LoginForm loginForm;

    @BeforeClass
    public void setupClass() {
        playwright = Playwright.create();
        browser = TestConfig.getBrowserType(playwright).launch(TestConfig.getBrowserLaunchOptions());
    }

    @BeforeMethod
    public void setupTest() {
        browserContext = browser.newContext(TestConfig.getNewContextOptions());
        page = browserContext.newPage();
        loginForm = new LoginForm(page);
    }

    @AfterClass
    public void tearDownClass() {
        if(browser != null) {
            browser.close();
        }
        if(playwright != null) {
            playwright.close();
        }
    }

    @Test
    public void tc04_LoginSuccess() {
        ExtentTestManager.info("Mo dropdown user");
        loginForm.openUserDropdown();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Mo form dang nhap");
        loginForm.displayLoginForm();
        page.waitForTimeout(1000);

        String email = TestConfig.getValidEmail();
        String password = TestConfig.getValidPassword();
        ExtentTestManager.info("Nhap thong tin dang nhap va dang nhap");
        loginForm.login(email, password);
        page.waitForTimeout(1000);

        Assert.assertTrue(loginForm.isDisplayMessageSuccess());
    }

    @Test
    public void tc05_LoginFailWithInvalidPassword() {
        ExtentTestManager.info("Mo dropdown user");
        loginForm.openUserDropdown();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Mo form dang nhap");
        loginForm.displayLoginForm();
        page.waitForTimeout(1000);

        String email = TestConfig.getValidEmail();
        String password = TestConfig.getInvalidPassword();
        ExtentTestManager.info("Nhap thong tin dang nhap va dang nhap");
        loginForm.login(email, password);
        page.waitForTimeout(1000);

        Assert.assertTrue(loginForm.isLoginFormStillVisible());
    }

    @Test
    public void tc06_LogOutSuccess() {
        ExtentTestManager.info("Mo dropdown user");
        loginForm.openUserDropdown();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Mo form dang nhap");
        loginForm.displayLoginForm();
        page.waitForTimeout(1000);

        String email = TestConfig.getValidEmail();
        String password = TestConfig.getValidPassword();
        ExtentTestManager.info("Nhap thong tin dang nhap va dang nhap");
        loginForm.login(email, password);
        page.waitForTimeout(1000);

        ExtentTestManager.info("Mo dropdown Uset button");
        loginForm.clickUserButton();
        page.waitForTimeout(1500);

        ExtentTestManager.info("Click nut Dang Xuat");
        loginForm.clickSignOutButton();
        page.waitForTimeout(1000);

        Assert.assertFalse(loginForm.isLoggedOut());
    }
}

