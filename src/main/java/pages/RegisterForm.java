package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.TestConfig;

public class RegisterForm extends BasePages{
    private static final String Name_Input = "input[name='name']";
    private static final String Email_Input = "input[name='email']";
    private static final String Password_Input = "input[name='password']";
    private static final String Phone_Input = "input[name='phone']";
    private static final String Birtday_Input = "input[name='birthday']";
    private static final String Gender_Input = "//input[@id='gender']/parent::span";
    private static final String Male_Option = "//div[@title='Nam']";
    private static final String User_Button = "//div[@id='user-dropdown']/following-sibling::button[1]";
    private static final String Register_Button = "//div[@id='root']//div/div//li//button[contains(text(), 'Đăng ký')]";
    private static final String Submit_Register_Button = "//button[@type='submit' and normalize-space()='Đăng ký']";
    private static final String Message_Register_Success = "//button[@type='submit' and contains(text(), 'Đăng nhập')]";
    private static final String Message_Register_Fail = "//div[contains(@class, 'modal') or contains(@class, 'popup')]//*[contains(text(), 'lỗi') or contains(text(), 'error')]";
    private static final String Message_Register_Fail_WeakPassword = "//span[contains(text(),'Mật khẩu không đủ mạnh')]";

    public RegisterForm(Page page) {
        super(page);
    }

    public void openUserDropdown() {
        String baseUrl = TestConfig.getBaseUrl();
        page.navigate(baseUrl);
        page.waitForLoadState();
        page.waitForSelector(User_Button);
        page.click(User_Button);
        System.out.println("B1: Mo dropdown user");
    }

    public void displayRegisterForm() {
        page.waitForSelector(Register_Button);
        page.click(Register_Button);
        System.out.println("B2: Mo form dang ky");
    }

    public void enterName(String name) {
        page.waitForSelector(Name_Input);
        page.fill(Name_Input, name);
        System.out.println("B3: Nhap ho ten: " + name);
    }

    public void enterEmail(String email) {
        page.waitForSelector(Email_Input);
        page.fill(Email_Input, email);
        System.out.println("B4: Nhap email: " + email);
    }

    public void enterPassword(String password) {
        page.waitForSelector(Password_Input);
        page.fill(Password_Input, password);
        System.out.println("B5: Nhap password: " + password);
    }

    public void enterPhone(String phone) {
        page.waitForSelector(Phone_Input);
        page.fill(Phone_Input, phone);
        System.out.println("B6: Nhap so dien thoai: " + phone);
    }

    public void enterBirtday(String birtday) {
        page.waitForSelector(Birtday_Input);
        page.fill(Birtday_Input, birtday);
        page.click(Birtday_Input);
        System.out.println("B7: Nhap ngay sinh: " + birtday);
    }

    public void openListGender() {
        page.waitForSelector(Gender_Input);
        page.click(Gender_Input);
        System.out.println("B8: Mo danh sach chon gioi tinh");
    }

    public void chooseGender() {
        page.waitForSelector(Male_Option);
        page.click(Male_Option);
        System.out.println("B9: Chon gioi tinh Nam");
    }

    public void submitRegisterForm() {
        page.waitForSelector(Submit_Register_Button);
        page.click(Submit_Register_Button);
        page.waitForLoadState();
        page.waitForTimeout(5000);
        System.out.println("B10: Nhan nut Dang ky");
    }



    public boolean displayMessageRegisterSuccess() {
        try {
            page.locator(Message_Register_Success)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(5000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public boolean displayErrorMessageFail() {
        try {
            Locator popup = page.locator(Message_Register_Fail);
            popup.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.ATTACHED)
                    .setTimeout(5000));
            return popup.isVisible();
        } catch (PlaywrightException p) {
            return false;
        }
    }

    public boolean isShowHighlightBorderAndErrorMessage() {
        Locator errorMessage = page.locator("div:has-text('Password')").first();
        errorMessage.waitFor(new Locator.WaitForOptions().setTimeout(3000).setState(WaitForSelectorState.ATTACHED));

        boolean isErrorVisible = false;
        if (errorMessage.count() > 0) {
            isErrorVisible = errorMessage.isVisible();
        }

        return isErrorVisible;
    }

}
