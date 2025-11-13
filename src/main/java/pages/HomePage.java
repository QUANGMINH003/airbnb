package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import constants.HomepageConstants;
import constants.UrlConstants;

import java.time.LocalDate;

import static constants.HomepageConstants.*;

public class HomePage extends BasePages{
    private static final String PlaceCard = "//h2[contains(text(),'Hồ Chí Minh')]/ancestor::a";

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
}
