package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginForm;
import pages.RegisterForm;
import pages.UserProfileForm;
import utils.ExtentTestManager;
import utils.TestConfig;

@Listeners(ExtentTestNGListener.class)
public class UserProfileTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private LoginForm loginForm;
    private UserProfileForm userProfileForm;
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
    public void tc22_AccessUserProfileSuccess() {
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

        ExtentTestManager.info("Mo dropdown user profile");
        userProfileForm.openUserProfileDropdown();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Hien thi user profile");
        userProfileForm.displayEditUserProfile();
        page.waitForTimeout(1000);

        Assert.assertTrue(
                userProfileForm.isUserProfileVisible(),
                "Trang hồ sơ người dùng KHÔNG hiển thị sau khi đăng nhập!"
        );
    }

    @Test
    public void tc23_UpdateInformationSuccess() {
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

        ExtentTestManager.info("Mo dropdown user profile");
        userProfileForm.openUserProfileDropdown();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Hien thi user profile");
        userProfileForm.displayEditUserProfile();
        page.waitForTimeout(1000);

        String name = TestConfig.getEditValidName();
        String mail = TestConfig.getEditValidEmail();
        String phone = TestConfig.getEditValidPhone();
        String birthday = TestConfig.getValidBirthDate();

        ExtentTestManager.info("Nhap cac gia tri hop le vao cac truong trong form edit profile");
        userProfileForm.editinfor(name, mail, phone, birthday);

        page.waitForTimeout(5000);

        Assert.assertTrue(userProfileForm.isDisplayMessageSuccess());
    }




}
