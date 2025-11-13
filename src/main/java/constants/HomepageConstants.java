package constants;

import java.time.format.DateTimeFormatter;

public class HomepageConstants {
    public static final String YEAR_SELECT = "//span[contains(@class, 'rdrMonthPicker')]/following-sibling::span[2]/select";
    public static final String MONTH_SELECT = "//span[contains(@class, 'rdrMonthPicker')]/select";
    public static final String CALENDAR_DAY = "//button[contains(@class, 'rdrDay') and not(contains(@class, 'rdrDayPassive')) and .//span[text()='%s']]";
    public static final String SearchButton = "//div[@id='root']/div[2]//div/div[5]//div//span";
    public static final String Date_Picker = "//div[@id='root']/div[2]//div/div[3]";
    public static final String Button_Add_Guest = "//p[contains(text(),'Thêm khách')]";
    public static final String Add_Button = "//div[contains(text(),'+')]";

    public static final String Button_Range_Price = "//button[normalize-space()='Giá']";

}
