package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.InvalidDeliveryInfoException;
import exception.MediaNotAvailableException;
import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;

public class PlaceRushOrderController extends BaseController{

    /**
     * For logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

    /**
     * Phương thức kiếm tra tính khả dụng của thông tin giao hàng nhanh và các sản phẩm trong giỏ hàng
     * @param expectedDate : Ngày giao hàng mong muốn
     * @param currDate : Ngày đặt hàng
     * @param address : địa chỉ giao hàng
     * @throws SQLException
     */
    public void placeRushOrder(Date expectedDate, Date currDate, String address) throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
        if(!validateDate(expectedDate, currDate)) {
            throw new InvalidDeliveryInfoException("Chosen date is invalid");
        }
        if(!validateRushAddress(address)) {
            throw new InvalidDeliveryInfoException("Address does not support Rush Order");
        }
    }

    /**
     * Xử lí thông tin giao hàng nhanh của khách hàng
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processRushDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateRushDeliveryInfo(info);
    }

    /**
     * validate Rush delivery information
     * @param info: thông tin giao hàng nhanh
     * @throws InterruptedException
     * @throws IOException
     */
    public void validateRushDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
        if(validatePhoneNumber(info.get("phone")) && validateName(info.get("name"))){

        }
        else throw new InvalidDeliveryInfoException("Some info is invalid");
    }

    /**
     * Xác nhận số điện thoại giao hàng nhanh
     * @param phoneNumber: số điện thoại khách hàng
     * @return giá trị kiểm tra đúng sai
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        // TODO: your work
        if (phoneNumber.length() !=10 || phoneNumber.charAt(0)!='0')
            return false;
        try{
            Integer.parseInt(phoneNumber);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }


    /**
     * Xác nhận tên của khách hàng
     * @param name: tên khách hàng
     * @return trả về giá trị kiểm tra đúng hay sai
     */
    public boolean validateName(String name) {
        // TODO: your work
        if (name.equals("") || name.trim().equals(""))
            return false;
        for(int i=0; i<name.length(); i++){
            int so = name.charAt(i);
            if ((so<65 || (so>90 && so < 97)  || so>122) && so !=32){
                return false;
            }
        }
        return true;
    }

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


    /**
     * xác nhận địa chỉ giao hàng nhanh
     * @param address: địa chỉ giao hàng nhanh của khách hàng
     * @return giá trị kiểm tra đúng sai của địa chỉ
     */
    public boolean validateRushAddress(String address) {
        if (address.equals("") || address.trim().equals(""))
            return false;
        for(int i=0; i<address.length(); i++){
            if (address.charAt(i)=='/' || address.charAt(i)==','||address.charAt(i)=='.' || address.charAt(i)==' ')
                continue;
            int so = address.charAt(i);
            if ((so>=0 && so<=47) ||(so>=58 && so <=64) || (so>90&&so<97) ||(so>122) ){
                return  false;}
        }
        String[] availableAddress ={"ha noi", "hà nội", "hanoi", "hn", "hồ chí minh", "ho chi minh", "hcm", "hochiminh"};
        for(int i =0 ;i <=availableAddress.length; i++){
            if (address.toLowerCase().contains(availableAddress[i]))
                    return true;
        }

        return false;
    }


    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}