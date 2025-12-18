package pages;

import com.microsoft.playwright.Page;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BasePages {
    protected final Page page;
    protected final String baseUrl = "https://demo5.cybersoft.edu.vn/";
    public BasePages(Page page) {
        this.page = page;
    }

    public void clickElement(String selector) {
        page.waitForSelector(selector);
        page.click(selector);
        page.waitForTimeout(1000);
    }

    public void fillElement(String selectors, String inputValue) {
        page.waitForSelector(selectors);
        page.fill(selectors, inputValue);
        page.waitForTimeout(1000);
    }
}
