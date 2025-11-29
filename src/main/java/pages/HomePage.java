package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import constants.HomepageConstants;
import constants.UrlConstants;

import java.time.LocalDate;

import static constants.HomepageConstants.*;

public class HomePage extends BasePages{
    public HomePage (Page page) {
        super(page);
    }

    public void navigateToWebsite() {
        String baseUrl = UrlConstants.BASE_URL;
        page.navigate(baseUrl);
        page.waitForLoadState();
    }

    public void clickPlaceCardHCM() {
        clickElement(PlaceCard);
        page.waitForTimeout(2000);
    }

    public void clickDatePicker() {
        clickElement(HomepageConstants.Date_Picker);
    }

    private String getMonthName(int month) {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return months[month - 1];
    }

    public void chooseDateBooking(String dateCI, String dateCO) {
        String[] dateParts = dateCI.split("/");
        String dayCI = dateParts[0];
        String monthNumberCI = dateParts[1];
        String yearCI = dateParts[2];
        String monthNameCI = getMonthName(Integer.parseInt(monthNumberCI));

        page.locator(YEAR_SELECT).selectOption(yearCI);
        page.locator(MONTH_SELECT).selectOption(monthNameCI);

        page.waitForSelector(String.format(HomepageConstants.CALENDAR_DAY, dayCI));

        page.locator(String.format(HomepageConstants.CALENDAR_DAY, dayCI)).first().click();

        String[] datePartsCO = dateCO.split("/");
        String dayCO = datePartsCO[0];
        String monthNumberCO = datePartsCO[1];
        String yearCO = datePartsCO[2];
        String monthNameCO = getMonthName(Integer.parseInt(monthNumberCO));

        page.locator(YEAR_SELECT).selectOption(yearCO);
        page.locator(MONTH_SELECT).selectOption(monthNameCO);

        page.waitForSelector(String.format(HomepageConstants.CALENDAR_DAY, dayCO));

        page.locator(String.format(HomepageConstants.CALENDAR_DAY, dayCO)).first().click();
    }

    public void AddQuantityGuest() {
        clickElement(Button_Add_Guest);
        clickElement(Add_Button);
    }

    public void clickSearchByPriceRangeButton() {
        clickElement(Range_Price_Button);
    }

    public void clickSearchButton() {

        clickElement(HomepageConstants.SearchButton);
    }

    public boolean isSearchSuccessbyPlace() {
        String currentUrl = page.url();
        return currentUrl.contains("/rooms/ho-chi-minh");
    }

    public boolean isSearchSuccess() {
        String currentUrl = page.url();
        return currentUrl.contains("/rooms");
    }

    public boolean isFilterApplied() {
        String currentUrl = page.url();
        return currentUrl.contains("priceMin=500000") && currentUrl.contains("priceMax=1000000");
    }

    public boolean isPriceFilterButtonReady() {
        Locator button = page.locator("#priceFilterButton");
        return button.isVisible() && button.isEnabled();
    }

    public boolean isPriceFilterVisible() {
        return page.isVisible("#priceFilterSection");
    }

    public boolean isOpenRoomDetailSuccess() {
        String currentUrl = page.url();
        return currentUrl.matches(".*\\/room-detail\\/\\d+");
    }
    public void displayListRoomsLocation() {
        clickElement(Location_Button);
        System.out.println("Hien thi list phong o dia diem da chon");
    }

    public boolean isListRoomLocationSuccess() {
        String currentUrl = page.url();
        return currentUrl.matches(".*\\/rooms\\/[a-zA-Z0-9\\-]+");
    }

    public void displayRoomsDetail() {
        clickElement(Room_Car);
        System.out.println("Hien thi chi tiet phong");
    }


    public boolean isRoomNameVisible() {
        return page.isVisible("//h2[@class=' font-bold text-3xl pt-4']");
    }

    public boolean isRoomAddressVisible() {
        return page.isVisible("//div[@class='flex gap-x-5']//a");

    }

    public boolean isRoomImageVisible() {
        return page.isVisible("//*[@id=\"root\"]/div[2]/div[2]/div/div[1]/div[1]/div/div");
    }

    public boolean isRoomPriceVisible() {
        return page.isVisible("//body[1]/div[1]/div[2]/div[3]/div[3]/div[1]/div[1]/div[1]");
    }

    public boolean isRoomUtilitiesVisible() {
        return page.isVisible("//div[@class='space-y-6']");
    }

    public boolean isRoomInformationVisible() {
        return page.isVisible("//body/div[@id='root']/div[contains(@class,'py-5 space-y-5')]/div[contains(@class,'grid grid-cols-1 lg:flex gap-5')]/div[1]//p");
    }

    public boolean isRoomDescriptionVisible() {
        return page.isVisible("//p[@class='text-justify py-3']");
    }

}
