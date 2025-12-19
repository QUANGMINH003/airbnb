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


import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

import static constants.RegisterConstants.Birtday_Input;
import static constants.UserProfileConstants.*;

public class UserProfileForm extends BasePages {

    public UserProfileForm(Page page) {
        super(page);
    }

    public void openUserProfileDropdown() {
        page.waitForLoadState();
        clickElement(User_Profile_Button);
        System.out.println("Mo dropdown user profile");
    }

    public void clickDashboardUserProfile() {
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

    public void accessEditUserProfile() {
        clickElement(Edit_Profile_Button);
        System.out.println("Truy cap edit user profile");
    }

    public void displayEditUserProfile() {
        clickElement(Edit_Profile_Form);
        System.out.println("Hien thi form edit user profile");
    }



    public void enterEditName(String name) {
        fillElement(Edit_Name_Input, name);
        System.out.println("Nhap ho ten: " + name);
    }


    public void enterEditPhone(String phone) {
        fillElement(Edit_Phone_Input, phone);
        System.out.println("Nhap so dien thoai: " + phone);
    }

    public void enterEditBirthday(String birthday) {
        clickElement(Edit_Birthday_Input);
        fillElement(Edit_Birthday_Input, birthday);
        page.keyboard().press("Enter");
        page.click("body");
        System.out.println("Nhap ngay sinh: " + birthday);
    }

    public void openEditListGender() {
        clickElement(Edit_Gender_Input);
        System.out.println("Mo danh sach chon gioi tinh");
    }

    public void chooseEditGender() {
        clickElement(Edit_Male_Option);
        System.out.println("Chon gioi tinh Nu");
    }

    /*public void submitEditProfileForm() {
        clickElement(Submit_EditProfile_Button);
        page.waitForLoadState();
        page.waitForTimeout(4000);
        System.out.println("Nhan nut Cap nhat");
    }*/

    public void submitEditProfileForm() {
        clickElement(Submit_EditProfile_Button);

        // ⏳ Đợi nút Save kết thúc loading (Ant Design)
        page.waitForSelector(
                ".ant-btn-loading",
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.DETACHED)
                        .setTimeout(10000)
        );

        System.out.println("Nhan nut Cap nhat");
    }


    public void editinfor( String name, String phone, String birthday) {

        enterEditName(name);
        enterEditPhone(phone);
        enterEditBirthday(birthday);
        openEditListGender();
        chooseEditGender();
        submitEditProfileForm();
    }

    public boolean isDisplayUpdateMessageSuccess() {
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

    public boolean isDisplayUpdateMessageFailed() {
        try {
            page.locator(Message_Update_Failed)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(3000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public void uploadAvatar(String filePath) {
        page.locator("input[type='file']").setInputFiles(Paths.get(filePath));
    }

    public void clickUploadAvatarButton() {
        clickElement(BUTTON_UPLOAD);
    }

    public boolean isUploadSuccess() {
        try {
            page.locator(MESSAGE_UPLOAD_SUCCESS)
                    .waitFor(new Locator.WaitForOptions()
                            .setTimeout(3000)
                            .setState(WaitForSelectorState.VISIBLE));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void openFormUploadImage() {
        clickElement(BUTTON_OPEN_UPLOAD);
        System.out.println("Mo form upload hinh anh");
    }



}


