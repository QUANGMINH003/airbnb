package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.TestConfig;
import static constants.RegisterConstants.*;


public class RegisterForm extends BasePages{
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
        clickElement(Register_Button);
        System.out.println("B2: Mo form dang ky");
    }

    public void enterName(String name) {
        fillElement(Name_Input, name);
        System.out.println("B3: Nhap ho ten: " + name);
    }

    public void enterEmail(String email) {
        fillElement(Email_Input, email);
        System.out.println("B4: Nhap email: " + email);
    }

    public void enterPassword(String password) {
        fillElement(Password_Input, password);
        System.out.println("B5: Nhap password: " + password);
    }

    public void enterPhone(String phone) {
        fillElement(Phone_Input, phone);
        System.out.println("B6: Nhap so dien thoai: " + phone);
    }

    public void enterBirtday(String birtday) {
        clickElement(Birtday_Input);
        fillElement(Birtday_Input, birtday);
//        page.click(Birtday_Input);
        System.out.println("B7: Nhap ngay sinh: " + birtday);
    }

    public void openListGender() {
        clickElement(Gender_Input);
        System.out.println("B8: Mo danh sach chon gioi tinh");
    }

    public void chooseGender() {
        clickElement(Male_Option);
        System.out.println("B9: Chon gioi tinh Nam");
    }

    public void submitRegisterForm() {
        clickElement(Submit_Register_Button);
        page.waitForLoadState();
        page.waitForTimeout(5000);
        System.out.println("B10: Nhan nut Dang ky");
    }

    public void register(String name, String email, String password, String phone, String birtday) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        enterPhone(phone);
        enterBirtday(birtday);
        openListGender();
        chooseGender();
        submitRegisterForm();
    }

    public boolean isRegisterSuccess() {
        try {
            page.locator(Check_Register_Success)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(2000));
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


    public boolean isShowErrorMessage() {
        Locator errorMessage = page.locator("div:has-text('Mật khẩu')").first();

        return errorMessage.isVisible(
                new Locator.IsVisibleOptions().setTimeout(0)
        );
    }

    public boolean isDisplayMessageInvalidEmail() {
        try{
            page.locator(Message_Invalid_Email)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(2000));
            return true;
        }catch (PlaywrightException e) {
            return false;
        }
    }

}
