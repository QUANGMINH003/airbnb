package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import constants.UrlConstants;
import utils.TestConfig;

public class LoginForm extends BasePages{
    private static final String Email_Input = "input[name='email']";
    private static final String Password_Input = "input[name='password']";
    private static final String Submit_Button = "//button[@type='submit' and contains(text(), 'Đăng nhập')]";
    private static final String Message_Login_Success = "//button[@id='user-menu-button']";
    private static final String Login_Button = "//div[@id='root']//div/div//li//button[contains(text(), 'Đăng nhập')]";
    private static final String User_Button = "//div[@id='user-dropdown']/following-sibling::button[1]";
    private static final String Logout_Button = "//div[@id='user-dropdown']//ul[1]/li[4]/button";

    public LoginForm(Page page) {super(page);}

    public void openUserDropdown() {
        String baseUrl = UrlConstants.BASE_URL;
        page.navigate(baseUrl);
        page.waitForLoadState();
        clickElement(User_Button);
        System.out.println("B1: Mo dropdown user");
    }

    public void displayLoginForm() {
        clickElement(Login_Button);
        System.out.println("B2: Mo form dang nhap");
    }

    public void enterEmail(String email) {
        fillElement(Email_Input, email);
        System.out.println("B3: Nhap email");
    }

    public void enterPassword(String password) {
        fillElement(Password_Input, password);
        System.out.println("B4: Nhap password");
    }

    public void  clickSubmitButton() {
        clickElement(Submit_Button);
        System.out.println("B5: CLick button [Dang nhap]");
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmitButton();
    }

    public boolean isDisplayMessageSuccess() {
        try {
            page.locator(Message_Login_Success)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(3000));

            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public boolean isLoginFormStillVisible() {
        try{
            page.locator(Submit_Button)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(3000));
            return true;
        }catch (PlaywrightException e){
            return false;
        }
    }

    public void clickUserButton() {
        clickElement(User_Button);
        System.out.println("Click button user");
    }

    public void clickSignOutButton() {
        clickElement(Logout_Button);
        System.out.println("Click button Dang Xuat");
    }

    public boolean isLoggedOut() {
        try {
            page.locator(User_Button)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(3000));
            return false;
        } catch (PlaywrightException e) {
            return true;
        }
    }
}

