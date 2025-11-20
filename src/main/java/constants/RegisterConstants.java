package constants;

public class RegisterConstants {
    public static final String Name_Input = "input[name='name']";
    public static final String Email_Input = "input[name='email']";
    public static final String Password_Input = "input[name='password']";
    public static final String Phone_Input = "input[name='phone']";
    public static final String Birtday_Input = "input[name='birthday']";
    public static final String Gender_Input = "//input[@id='gender']/parent::span";
    public static final String Male_Option = "//div[@title='Nam']";
    public static final String User_Button = "//div[@id='user-dropdown']/following-sibling::button[1]";
    public static final String Register_Button = "//div[@id='root']//div/div//li//button[contains(text(), 'Đăng ký')]";
    public static final String Submit_Register_Button = "//button[@type='submit' and normalize-space()='Đăng ký']";
    public static final String Check_Register_Success = "//button[@type='submit' and contains(text(), 'Đăng nhập')]";
    public static final String Message_Register_Fail = "//div[contains(@class, 'modal') or contains(@class, 'popup')]//*[contains(text(), 'lỗi') or contains(text(), 'error')]";
    public static final String Message_Invalid_Email = "//div[contains(text(),'Vui lòng nhập đúng định dạng email')]";
}
