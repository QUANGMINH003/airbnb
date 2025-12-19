package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginForm;
import pages.UserProfileForm;
import utils.ExtentTestManager;
import utils.RandomFileUtils;
import utils.TestConfig;


@Listeners(ExtentTestNGListener.class)
public class UserProfileTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private LoginForm loginForm;
    private UserProfileForm userProfileForm;

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
        userProfileForm = new UserProfileForm(page);

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

        ExtentTestManager.info("Click mo dashboard");
        userProfileForm.clickDashboardUserProfile();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Truy cap user profile");
        userProfileForm.accessEditUserProfile();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Hien thi user profile");
        userProfileForm.displayEditUserProfile();
        page.waitForTimeout(1000);

        Assert.assertTrue(
                userProfileForm.isUserProfileVisible(),
                "Trang ho so nguoi dung KHONG hien thi sau khi dang nhap!"
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

        ExtentTestManager.info("Click mo dashboard");
        userProfileForm.clickDashboardUserProfile();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Hien thi user profile");
        userProfileForm.displayEditUserProfile();
        page.waitForTimeout(2000);

        String name = TestConfig.getEditValidName();
        String phone = TestConfig.getEditValidPhone();
        String birthday = TestConfig.getEditValidBirthday();

        ExtentTestManager.info("Nhap cac gia tri hop le vao cac truong trong form edit profile");
        userProfileForm.editinfor(name, phone, birthday);

        Assert.assertTrue(userProfileForm.isDisplayUpdateMessageSuccess(),"Cap nhat thong tin khong thanh cong!");
    }

    @Test
    public void tcUploadAvatar() {
        // Folder ảnh thật trong máy bạn
        String imageFolder = "D:\\";

        // Lấy file JPG ngẫu nhiên
        String randomAvatar = RandomFileUtils.getRandomJpg(imageFolder);

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

        ExtentTestManager.info("Click mo dashboard");
        userProfileForm.clickDashboardUserProfile();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Mo Form Upload hinh anh");
        userProfileForm.openFormUploadImage();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Upload file");
        userProfileForm.uploadAvatar(randomAvatar);
        page.waitForTimeout(1000);

        ExtentTestManager.info("Click upload");
        userProfileForm.clickUploadAvatarButton();
        page.waitForTimeout(1000);

        Assert.assertTrue(userProfileForm.isUploadSuccess(), "Upload avatar thất bại!");
    }

}
