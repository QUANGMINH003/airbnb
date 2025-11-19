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

    @Test
    public void tc11_SearchByPriceRange() {
        ExtentTestManager.info("Truy cap website");
        homePage.navigateToWebsite();

        ExtentTestManager.info("Kiem tra nut mo filter khoang gia co kha dung khong");
        Assert.assertTrue(homePage.isPriceFilterButtonReady(),
                "Button mo filter khoang gia KHONG kha dung (an hoac disable)!");

        ExtentTestManager.info("Mo Filter theo khoang gia");
        homePage.clickSearchByPriceRangeButton();
        page.waitForTimeout(1000);

        ExtentTestManager.info("Kiem tra filter khoang gia da mo ra chua");
        Assert.assertTrue(homePage.isPriceFilterVisible(),
                "Click nut mo filter khoang gia NHUNG khong co phan tu filter hien thi!");

        ExtentTestManager.info("Thuc hien tim kiem");
        homePage.clickSearchButton();
        page.waitForTimeout(5000);

        Assert.assertTrue(homePage.isFilterApplied(), "Bo loc khoang gia KHONG duoc ap dung sau khi tim kiem!");
    }

    @Test
    public void tc12_SeeRoomDetails() {
        ExtentTestManager.info("Truy cap website");
        homePage.navigateToWebsite();

        ExtentTestManager.info("Truy cap khu vuc muon tim phong");
        homePage.displayListRoomsLocation();

        ExtentTestManager.info("Xem chi tiet phong");
        homePage.displayRoomsDetail();

        Assert.assertTrue(homePage.isOpenRoomDetailSuccess(), "Khong xem duoc chi tiet phong!");
    }

    @Test
    public void tc13_FullRoomInformationDisplay() {
        ExtentTestManager.info("Truy cap website");
        homePage.navigateToWebsite();

        ExtentTestManager.info("Truy cap khu vuc muon tim phong");
        homePage.displayListRoomsLocation();

        ExtentTestManager.info("Xem chi tiet phong");
        homePage.displayRoomsDetail();

        Assert.assertTrue(homePage.isOpenRoomDetailSuccess(), "Khong xem duoc chi tiet phong!");
        Assert.assertTrue(homePage.isRoomNameVisible(), "Khong hien thi ten phong!");
        Assert.assertTrue(homePage.isRoomAddressVisible(), "Khong hien thi dia chi phong!");
        Assert.assertTrue(homePage.isRoomImageVisible(), "Khong hien thi hinh anh phong!");
        Assert.assertTrue(homePage.isRoomPriceVisible(), "Khong hien thi gia phong!");
        Assert.assertTrue(homePage.isRoomUtilitiesVisible(), "Khong hien thi tien ich phong!");
        Assert.assertTrue(homePage.isRoomInformationVisible(), "Khong hien thi thong tin tom tat phong!");
        Assert.assertTrue(homePage.isRoomDescriptionVisible(), "Khong hien thi mo ta phong!");
    }
}
