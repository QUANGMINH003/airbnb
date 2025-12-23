package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.*;
import pages.BookingFlowPage;
import pages.HomePage;
import pages.LoginForm;
import pages.UserProfileForm;
import utils.ExtentTestManager;
import utils.TestConfig;


@Listeners(ExtentTestNGListener.class)
public class BookingFlowTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private LoginForm loginForm;
    private UserProfileForm userProfileForm;
    private BookingFlowPage bookingFlowPage;
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
        loginForm = new LoginForm(page);
        userProfileForm = new UserProfileForm(page);
        bookingFlowPage = new BookingFlowPage(page);
        homePage = new HomePage(page);
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
    //  TC16: Đặt phòng thành công (end-to-end flow )
    public void TC16_BookingRoom(){
        ExtentTestManager.info("Truy cap website va dang nhap");
        loginForm.openUserDropdown();
        page.waitForTimeout(1000);

        loginForm.displayLoginForm();
        page.waitForTimeout(1000);

        String email = TestConfig.getValidEmail();
        String password = TestConfig.getValidPassword();
        ExtentTestManager.info("Nhap thong tin dang nhap va dang nhap");
        loginForm.login(email, password);
        page.waitForTimeout(1000);

        ExtentTestManager.info("Tim kiem phong");
        bookingFlowPage.selectLocation();
        page.waitForTimeout(2000);

        ExtentTestManager.info("Mo table chon ngay checkin va checkout");
        homePage.clickDatePicker();
        homePage.chooseDateBooking(TestConfig.getCheckinDate(), TestConfig.getCheckoutDate());
        ExtentTestManager.info("Thuc hien tim kiem");
        homePage.clickSearchButton();

        ExtentTestManager.info("Thực hiện đặt phòng");
        bookingFlowPage.LinkRoom();
        page.waitForTimeout(2000);

        bookingFlowPage.confirmBooking();

        ExtentTestManager.info("Thực hiện kiem tra thong bao thanh cong");
        bookingFlowPage.checkNotification();

    }

    //- TC17: Validate tính toán giá chính xác
    @Test
    public void TC17_Validate (){
        ExtentTestManager.info("Truy cap website va dang nhap");
        loginForm.openUserDropdown();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Tim kiem phong");
        bookingFlowPage.selectLocation();
        page.waitForTimeout(2000);

        ExtentTestManager.info("Mo table chon ngay checkin va checkout");
        homePage.clickDatePicker();
        homePage.chooseDateBooking(TestConfig.getCheckinDate(), TestConfig.getCheckoutDate());
        ExtentTestManager.info("Thuc hien tim kiem");
        homePage.clickSearchButton();

        ExtentTestManager.info("Thực hiện đặt phòng");
        bookingFlowPage.LinkRoom();

        bookingFlowPage.validateDynamicPriceCalculation();

    }

    //- TC18: Đặt phòng thất bại - Chưa đăng nhập
    @Test
    public void TC18_Booking_Fail_NotLoggedIn (){
        ExtentTestManager.info("Truy cap website");
        homePage.navigateToWebsite();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Tim kiem phong");
        bookingFlowPage.selectLocation();
        page.waitForTimeout(2000);

        ExtentTestManager.info("Mo table chon ngay checkin va checkout");
        homePage.clickDatePicker();
        homePage.chooseDateBooking(TestConfig.getCheckinDate(), TestConfig.getCheckoutDate());
        ExtentTestManager.info("Thuc hien tim kiem");
        homePage.clickSearchButton();

        ExtentTestManager.info("Thực hiện đặt phòng");
        bookingFlowPage.LinkRoom();
        page.waitForTimeout(2000);

        ExtentTestManager.info("Kiem tra thong bao yeu cau dang nhap");
        bookingFlowPage.checkLoginRequiredNotification();
    }

    //- TC19: Đặt phòng thất bại - Ngày không hợp lệ
    @Test
    public void TC19_Booking_Fail_PastCheckInOut(){
        ExtentTestManager.info("Truy cap website va dang nhap");
        loginForm.openUserDropdown();
        page.waitForTimeout(1000);

        loginForm.displayLoginForm();
        page.waitForTimeout(1000);

        String email = TestConfig.getValidEmail();
        String password = TestConfig.getValidPassword();
        ExtentTestManager.info("Nhap thong tin dang nhap va dang nhap");
        loginForm.login(email, password);
        page.waitForTimeout(1000);

        ExtentTestManager.info("Tim kiem phong");
        bookingFlowPage.selectLocation();
        page.waitForTimeout(2000);

        ExtentTestManager.info("Mo table chon ngay checkin va checkout");
        homePage.clickDatePicker();

        ExtentTestManager.info("Kiem tra xem ngày trong qua khu co cho phep chon k");
        bookingFlowPage.PastDate();
    }

    //- TC20: Xem lịch sử đặt phòng
    @Test
    public void TC20_CheckHistoryRoom(){
        // dang nhap
        loginForm.openUserDropdown();
        page.waitForTimeout(1000);

        loginForm.displayLoginForm();
        page.waitForTimeout(1000);

        String email = TestConfig.getValidEmail();
        String password = TestConfig.getValidPassword();
        ExtentTestManager.info("Nhap thong tin dang nhap va dang nhap");
        loginForm.login(email, password);
        page.waitForTimeout(1000);

        // click vao User-menu-button
        ExtentTestManager.info("Mo dropdown user profile");
        userProfileForm.openUserProfileDropdown();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Click mo dashboard");
        userProfileForm.clickDashboardUserProfile();
        page.waitForTimeout(3000);
        // kiem tra
        ExtentTestManager.info("Tim Room card có thu tu dau tien trong danh sach");
        bookingFlowPage.CheckRoomCard();
    }
}
