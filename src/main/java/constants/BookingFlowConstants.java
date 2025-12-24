package constants;

public class BookingFlowConstants {
    private static final String BOOKING_ROOM_LINK1 = "a[href='/room-detail/1']";
    public static final String Location_link ="div:has(> p:has-text('Địa điểm'))" ;
    public static final String LocationIMG_HCM = "div.cursor-pointer:has(> p:text-is('Hồ Chí Minh')) > div > img";
    public static final String Location_Test = "a:has(\n" + "  div.relative:has(> p:text-is('NewApt D1 - Cozy studio - NU apt - 500m Bui Vien!'))\n" + ")";
    public static final String Button_Booking = "div.p-6:has(button:text-is('Đặt phòng')) button.bg-main:text-is('Đặt phòng')";
    public static final String ButtonBooking_XacNhan = "div.space-y-5 button:text-is('Xác nhận')";
    public static final String Success_Notification = "div.ant-notification-notice-message:text-is('Thêm mới thành công!')";
    public static final String Room_Card = ".ant-card-body";
    public static final String Login_Required_Notification = ".ant-notification-notice-warning:has-text('Vui lòng đăng nhập')";
    public static final String STAY_INFO_TEXT = "p.underline.text-base:has-text('X')"; // Lấy dòng "$28 X 7 nights"
    public static final String CLEANING_FEE_VALUE = "div.flex.justify-between:has(p:text-is('Cleaning fee')) p.font-mono";
    public static final String TOTAL_PRICE_VALUE = "div.flex.justify-between:has(p:text-is('Total before taxes')) p.font-mono";
}
