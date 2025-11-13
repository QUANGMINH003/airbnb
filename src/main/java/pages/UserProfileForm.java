package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import constants.UrlConstants;
import utils.TestConfig;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

public class UserProfileForm extends BasePages {
    // ===================== SELECTORS =====================
    private static final String User_Profile_Button = "//button[@id='user-menu-button']";
    private static final String User_Profile_Dashboard = "//a[normalize-space()='Dashboard']";
    private static final String Edit_Profile_Button = "//button[contains(text(),'Chỉnh sửa hồ sơ')]";
    private static final String Message_Update_Success = "//span[contains(text(),'Cập nhật thông tin thành công')]";
    private static final String Avatar_Profile_User = "//img[@class='mx-auto w-36 h-36 object-cover rounded-full']";
    private static final String Submit_EditProfile_Button = "//button[@class='ant-btn css-mzwlov ant-btn-primary bg-blue-500']";
    private static final String Edit_Name_Input = "//input[@id='name']";
    private static final String Edit_Email_Input = "//input[@id='email']";
    //chua update
    private static final String Edit_Password_Input = "input[name='password']";
    //
    private static final String Edit_Phone_Input = "//input[@id='phone']";
    private static final String Edit_Birtday_Input = "//input[@id='birthday']";
    private static final String Edit_Gender_Input = "//input[@id='gender']/parent::span";
    private static final String Edit_Male_Option = "//div[@title='Nữ']";

    public UserProfileForm(Page page) {
        super(page);
    }

    public void openUserProfileDropdown() {
        clickElement(User_Profile_Button);
        System.out.println("Mo dropdown user profile");
    }

    public void displayUserProfile() {
        clickElement(User_Profile_Dashboard);
        System.out.println("Mo form user profile");
    }

    public boolean isUserProfileVisible() {
        try {
            page.locator(Avatar_Profile_User)
                    .waitFor(new Locator.WaitForOptions()
                            .setTimeout(3000)
                            .setState(WaitForSelectorState.VISIBLE));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void displayEditUserProfile() {
        clickElement(Edit_Profile_Button);
        System.out.println("Mo form edit user profile");
    }

    public void openProfilePage() {

    }

    public void enterEditName(String name) {
        fillElement(Edit_Name_Input, name);
        System.out.println("Nhap ho ten: " + name);
    }

    public void enterEditEmail(String mail) {
        fillElement(Edit_Email_Input, mail);
        System.out.println("Nhap email: " + mail);
    }

    /*public void enterEditPassword(String password) {
        fillElement(Edit_Password_Input, password);
        System.out.println("Nhap password: " + password);
    }*/

    public void enterEditPhone(String phone) {
        fillElement(Edit_Phone_Input, phone);
        System.out.println("Nhap so dien thoai: " + phone);
    }

    public void enterEditBirtday(String birtday) {
        fillElement(Edit_Birtday_Input, birtday);
//        page.click(Birtday_Input);
        System.out.println("Nhap ngay sinh: " + birtday);
    }

    public void openEditListGender() {
        clickElement(Edit_Gender_Input);
        System.out.println("Mo danh sach chon gioi tinh");
    }

    public void chooseEditGender() {
        clickElement(Edit_Male_Option);
        System.out.println("Chon gioi tinh Nu");
    }

    public void submitEditProfileForm() {
        clickElement(Submit_EditProfile_Button);
        page.waitForLoadState();
        page.waitForTimeout(5000);
        System.out.println("Nhan nut Cap nhat");
    }

    public void editinfor(String name, String mail, String phone, String birtday) {
        enterEditName(name);
        enterEditEmail(mail);
        enterEditPhone(phone);
        enterEditBirtday(birtday);
        openEditListGender();
        chooseEditGender();
        submitEditProfileForm();
    }

    public boolean isDisplayMessageSuccess() {
        try {
            page.locator(Message_Update_Success)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(3000));

            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

}


