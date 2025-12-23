package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static constants.BookingFlowConstants.*;


public class BookingFlowPage extends BasePages {
    public BookingFlowPage(Page page) {
        super(page);
    }

    public void selectLocation(){
        clickElement(Location_link);
        System.out.println("B6: Click vao dia diem");
        clickElement(LocationIMG_HCM);
        System.out.println("B7: Chon thanh cong dia diem HCM");
    }

    public void LinkRoom(){
        clickElement(Location_Test);
        System.out.println("B8: Chọn phòng thanh cong");
        clickElement(Button_Booking);
        System.out.println("B9: Nhan dat phong");
    }

    public void confirmBooking(){
        clickElement(ButtonBooking_XacNhan);
        System.out.println("B10: Xac nhan thanh cong");
    }

    public void checkNotification(){
        Locator successMsg = page.locator(Success_Notification);
        assertThat(successMsg).isVisible();
        System.out.println("Hien thi thong bao thanh cong");
    }

    public void PastDate(){
        String yesterday = java.time.LocalDate.now().minusDays(1).toString();
        Locator yesterdayCell = page.locator("td[title='" + yesterday + "']");

        if (yesterdayCell.isVisible()) {
            String classValue = yesterdayCell.getAttribute("class");
            if (classValue.contains("ant-picker-cell-disabled") || classValue.contains("disabled")) {
                System.out.println("PASS: Ngày quá khứ đã bị khóa.");
            } else {
                throw new AssertionError("FAIL: Ngày quá khứ vẫn cho phép chọn!");
            }
        } else {
            System.out.println("Ngày quá khứ đã ẩn và không thể chọn trong lịch.");
        }
    }

    public void checkLoginRequiredNotification(){
        Locator thongbaoYC = page.locator(Login_Required_Notification);
        if (thongbaoYC.isVisible()) {
            System.out.println("Nút thông báo đang hiển thị.");
        } else {
            System.out.println("Nút thông báo chưa hiển thị.");
        }
    }

    public void CheckRoomCard(){
        Locator card = page.locator(Room_Card).first();
        if (card.isVisible()) {
            System.out.println("Card hiển thị thành công");
        }else {
            System.out.println("Card chưa hiển thị.");
        }
    }

    public void validateDynamicPriceCalculation() {

        String stayInfo = page.locator(STAY_INFO_TEXT).textContent();

        String[] parts = stayInfo.split("X");
        double pricePerNight = Double.parseDouble(parts[0].replaceAll("[^0-9]", ""));
        int numberOfNights = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));

        double cleaningFee = Double.parseDouble(page.locator(CLEANING_FEE_VALUE).textContent().replaceAll("[^0-9]", ""));
        double actualTotalOnUI = Double.parseDouble(page.locator(TOTAL_PRICE_VALUE).textContent().replaceAll("[^0-9]", ""));

        double expectedStayPrice = pricePerNight * numberOfNights;
        double expectedFinalTotal = expectedStayPrice + cleaningFee;

        System.out.println("--- Kiểm tra tính toán ---");
        System.out.println("Giá 1 đêm: " + pricePerNight);
        System.out.println("Số đêm: " + numberOfNights);
        System.out.println("Phí vệ sinh: " + cleaningFee);
        System.out.println("=> Tổng tính toán: " + expectedFinalTotal);
        System.out.println("=> Tổng trên Web: " + actualTotalOnUI);

        if (actualTotalOnUI == expectedFinalTotal) {
            System.out.println("Tính toán tự động chính xác");
        } else {
            throw new AssertionError(" Sai lệch Kỳ vọng " + expectedFinalTotal + " nhưng UI hiển thị " + actualTotalOnUI);
        }
    }

}
