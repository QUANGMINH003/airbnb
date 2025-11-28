package constants;

public class UserProfileConstants {
    public static final String User_Profile_Button = "//button[@id='user-menu-button']";
    public static final String User_Profile_Dashboard = "//a[normalize-space()='Dashboard']";
    public static final String Edit_Profile_Button = "//button[contains(text(),'Chỉnh sửa hồ sơ')]";
    public static final String Message_Update_Success = "//span[contains(text(),'Cập nhật thông tin thành công')]";
    public static final String Message_Update_Failed = "";
    public static final String Avatar_Profile_User = "//img[@class='mx-auto w-36 h-36 object-cover rounded-full']";
    public static final String Submit_EditProfile_Button = "//button[@class='ant-btn css-mzwlov ant-btn-primary bg-blue-500']";
    public static final String Edit_Name_Input = "//input[@id='name']";
    public static final String Edit_Email_Input = "//input[@id='email']";
    public static final String Edit_Profile_Form = "//div[@class='ant-modal-content']";
    public static final String Edit_Phone_Input = "//input[@id='phone']";
    public static final String Edit_Birtday_Input = "//input[@id='birthday']";
    public static final String Edit_Gender_Input = "//input[@id='gender']/parent::span";
    public static final String Edit_Male_Option = "//div[@title='Nữ']";

    public static final String BUTTON_OPEN_UPLOAD = "//button[contains(text(),'Cập nhật ảnh')]";

    public static final String INPUT_AVATAR = "input[type='file']";
    public static final String BUTTON_UPLOAD = "//button[normalize-space()='Upload Avatar']";

    public static final String MESSAGE_UPLOAD_SUCCESS = "//div[@class='ant-message-notice-content']";
}
