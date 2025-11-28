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

import static constants.UserProfileConstants.*;

public class UserProfileForm extends BasePages {

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

    public void enterEditEmail(String mail) {
        fillElement(Edit_Email_Input, mail);
        System.out.println("Nhap email: " + mail);
    }

    public void enterEditPhone(String phone) {
        fillElement(Edit_Phone_Input, phone);
        System.out.println("Nhap so dien thoai: " + phone);
    }

    public void enterEditBirtday(String birtday) {
        fillElement(Edit_Birtday_Input, birtday);
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

    public static String getRandomJpg(String directoryPath) {
        File folder = new File(directoryPath);

        File[] jpgFiles = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg")
        );

        if (jpgFiles == null || jpgFiles.length == 0) {
            throw new RuntimeException("Không tìm thấy file JPG trong thư mục: " + directoryPath);
        }

        Random random = new Random();
        File randomFile = jpgFiles[random.nextInt(jpgFiles.length)];

        return randomFile.getAbsolutePath();
    }


}


