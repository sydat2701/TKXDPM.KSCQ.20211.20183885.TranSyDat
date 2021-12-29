package validation;

import java.util.Calendar;
import java.util.Date;

public class DateValidation {

    /**
     * Xác nhận ngày giao hàng và ngày đặt hàng có hợp lệ không
     * @param expectedDate: ngày giao hàng
     * @param date: ngày đặt hàng
     * @return giá trị kiểm tra đúng hay sai
     */
    public boolean validateDate(Date expectedDate, Date date) { // Kiểm tra ngày nhận hàng và ngày đặt hàng
        // có hợp lệ theo logic thời gian hay không
        Date currDate = Calendar.getInstance().getTime();
        if (expectedDate.after(date)) return true;
        return false;
    }
}
