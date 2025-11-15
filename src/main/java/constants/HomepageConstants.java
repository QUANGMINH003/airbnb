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

    public static final String Range_Price_Button = "//button[normalize-space()='Giá']";

    public static final String Location_Button = "//a[@href='/rooms/ho-chi-minh']//div[@class='ant-card ant-card-bordered ant-card-hoverable w-full flex items-center cursor-pointer hover:bg-gray-100 hover:scale-105 transition duration-300 ease-in-out css-mzwlov']";

    public static final String Room_Car = "//body/div[@id='root']/div[@class='mx-auto container grid grid-cols-1 lg:grid-cols-2 gap-3']/div[@class='py-12 space-y-3 h-auto']/div[@class='space-y-6']/div[1]/div[1]/a[1]/div[1]/div[1]";


}
