package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegisterForm;
import utils.ExtentTestManager;
import utils.TestConfig;


@Listeners(ExtentTestNGListener.class)
public class RegisterTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private RegisterForm registerForm;

    @BeforeClass
    public void setupClass() {
        playwright = Playwright.create();
        browser = TestConfig.getBrowserType(playwright).launch(TestConfig.getBrowserLaunchOptions());
    }

    @BeforeMethod
    public void setupTest() {
        browserContext = browser.newContext(TestConfig.getNewContextOptions());
        page = browserContext.newPage();
        registerForm = new RegisterForm(page);
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
    public void tc01_RegisterSuccessWithValidInfo() {
        ExtentTestManager.info("Mo dropdown user");
        registerForm.openUserDropdown();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Mo form dang ky");
        registerForm.displayRegisterForm();
        page.waitForTimeout(1000);

        String name = TestConfig.getValidName();
        String email = TestConfig.getValidEmail();
        String password = TestConfig.getValidPassword();
        String phone = TestConfig.getValidPhone();
        String birthday = TestConfig.getValidBirthDate();
        ExtentTestManager.info("Nhap cac gia tri hop le vao cac truong trong form dang ky");
        registerForm.enterName(name);
        registerForm.enterEmail(email);
        registerForm.enterPassword(password);
        registerForm.enterPhone(phone);
        registerForm.enterBirtday(birthday);
        registerForm.openListGender();
        registerForm.chooseGender();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Submit form dang ky");
        registerForm.submitRegisterForm();

        Assert.assertTrue(registerForm.displayMessageRegisterSuccess());
    }

    @Test
    public void tc02_TestRegisterFails_EmailAlreadyExists() {
        ExtentTestManager.info("Mo dropdown user");
        registerForm.openUserDropdown();
        page.waitForTimeout(2000);

        ExtentTestManager.info("Mo form dang ky");
        registerForm.displayRegisterForm();
        page.waitForTimeout(2000);

        String name = TestConfig.getValidName();
        String email = TestConfig.getValidEmail();
        String password = TestConfig.getValidPassword();
        String phone = TestConfig.getValidPhone();
        String birthday = TestConfig.getValidBirthDate();

        ExtentTestManager.info("Nhap cac gia tri vao cac truong trong form dang ky");
        registerForm.enterName(name);
        registerForm.enterEmail(email);
        registerForm.enterPassword(password);
        registerForm.enterPhone(phone);
        registerForm.enterBirtday(birthday);
        registerForm.openListGender();
        registerForm.chooseGender();
        page.waitForTimeout(3000);

        ExtentTestManager.info("Submit form dang ky");
        registerForm.submitRegisterForm();

        Assert.assertFalse(registerForm.displayMessageRegisterSuccess());
    }

    @Test
    public void tc03_TestRegisterFailWithWeakPassword() {
        ExtentTestManager.info("Mo dropdown user");
        registerForm.openUserDropdown();
        page.waitForTimeout(2000);

        ExtentTestManager.info("Mo form dang ky");
        registerForm.displayRegisterForm();
        page.waitForTimeout(2000);

        String name = TestConfig.getValidName();
        String email = TestConfig.getValidEmail();
        String password = TestConfig.getWeakPassword();
        String phone = TestConfig.getValidPhone();
        String birthday = TestConfig.getValidBirthDate();

        ExtentTestManager.info("Nhap cac gia tri vao cac truong trong form dang ky");
        registerForm.enterName(name);
        registerForm.enterEmail(email);
        registerForm.enterPassword(password);
        registerForm.enterPhone(phone);
        registerForm.enterBirtday(birthday);
        registerForm.openListGender();
        registerForm.chooseGender();
        page.waitForTimeout(3000);

        ExtentTestManager.info("Submit form dang ky");
        registerForm.submitRegisterForm();

        boolean result = registerForm.isShowHighlightBorderAndErrorMessage();
        Assert.assertTrue(result, "Expected password input to show error highlight and error message");
    }
}
