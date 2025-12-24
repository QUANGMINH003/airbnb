package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static constants.BookingFlowConstants.*;
import static constants.LoginFormConstants.Submit_Button;
import static constants.LoginFormConstants.User_Button;
import static constants.UserProfileConstants.MESSAGE_UPLOAD_SUCCESS;


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

    public boolean ischeckNotification() {
        try {
            page.locator(Success_Notification)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(3000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }


    public boolean isPastDateDisabled() {

        String yesterday = java.time.LocalDate.now().minusDays(1).toString();

        Locator yesterdayCell = page.locator("button.rdrDayDisabled").first();

        try {
            yesterdayCell.waitFor(new Locator.WaitForOptions().setTimeout(3000));
            String classValue = yesterdayCell.getAttribute("class");

            System.out.println("Ngay da duoc disabled");

            return classValue.contains("rdrDayDisabled");

        } catch (Exception e) {
            return false;
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

    public boolean ischeckLoginRequiredNotification() {
        try {
            page.locator(Login_Required_Notification)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(3000));
            return true;
        } catch (PlaywrightException e) {
            return false;
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

    public boolean isCheckRoomCard() {
        try {
            page.locator(Room_Card)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(3000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public boolean isDynamicPriceCalculationCorrect() {

        String stayInfo = page.locator(STAY_INFO_TEXT).textContent();
        String[] parts = stayInfo.split("X");

        double pricePerNight = Double.parseDouble(parts[0].replaceAll("[^0-9]", ""));
        int numberOfNights = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
        double cleaningFee = Double.parseDouble(page.locator(CLEANING_FEE_VALUE).textContent().replaceAll("[^0-9]", ""));
        double actualTotalOnUI = Double.parseDouble(page.locator(TOTAL_PRICE_VALUE).textContent().replaceAll("[^0-9]", ""));

        double expectedFinalTotal = (pricePerNight * numberOfNights) + cleaningFee;

        System.out.println("Tổng tính toán: " + expectedFinalTotal + " | Tổng UI: " + actualTotalOnUI);

        return Double.compare(expectedFinalTotal, actualTotalOnUI) == 0;
    }
}
