package validation;

import exception.InvalidDeliveryInfoException;

import java.io.IOException;
import java.util.HashMap;

public class DeliveryInfoValidation {
    private PhoneValidation phoneValidation;
    private NameValidation nameValidation;

    /**
     * The method validates the info
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
        if(phoneValidation.validatePhoneNumber(info.get("phone")) && nameValidation.validateName(info.get("name"))){

        }
        else throw new InvalidDeliveryInfoException("Some info is invalid");
    }
}
