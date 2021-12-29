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
import subsystem.IShippingFee;
import validation.*;

public class PlaceRushOrderController extends BaseController implements IShippingFee {

    /**
     * For logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

    private DeliveryInfoValidation deliveryInfoValidation;
    private RushAddressValidation rushAddressValidation;
    private DateValidation dateValidation;

    /**
     * Phương thức kiếm tra tính khả dụng của thông tin giao hàng nhanh và các sản phẩm trong giỏ hàng
     * @param expectedDate : Ngày giao hàng mong muốn
     * @param currDate : Ngày đặt hàng
     * @param address : địa chỉ giao hàng
     * @throws SQLException
     */
    public void placeRushOrder(Date expectedDate, Date currDate, String address) throws SQLException{
        cart.checkAvailabilityOfProduct();
        if(!dateValidation.validateDate(expectedDate, currDate)) {
            throw new InvalidDeliveryInfoException("Chosen date is invalid");
        }
        if(!rushAddressValidation.validateRushAddress(address)) {
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
        deliveryInfoValidation.validateDeliveryInfo(info);
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
     * This method calculates the shipping fees of order
     * @param amount
     * @return shippingFee
     */
    @Override
    public float calculateShippingFee(float amount){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * amount);
        LOGGER.info("Order Amount: " + amount + " -- Shipping Fees: " + fees);
        return fees;
    }
}