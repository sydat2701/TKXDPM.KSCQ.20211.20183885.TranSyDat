package validation;

public class PhoneValidation {
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
}
