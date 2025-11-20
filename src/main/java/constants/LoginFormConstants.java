package constants;

public class LoginFormConstants {
    public static final String Email_Input = "input[name='email']";
    public static final String Password_Input = "input[name='password']";
    public static final String Submit_Button = "//button[@type='submit' and contains(text(), 'Đăng nhập')]";
    public static final String Message_Login_Success = "//button[@id='user-menu-button']";
    public static final String Login_Button = "//div[@id='root']//div/div//li//button[contains(text(), 'Đăng nhập')]";
    public static final String User_Button = "//div[@id='user-dropdown']/following-sibling::button[1]";
    public static final String Logout_Button = "//div[@id='user-dropdown']//ul[1]/li[4]/button";
}
