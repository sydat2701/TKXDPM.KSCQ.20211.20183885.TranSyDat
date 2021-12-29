package validation;

public class AddressValidation {

    public boolean validateAddress(String address) {
        // TODO: your work
        if (address.equals("") || address.trim().equals(""))
            return false;
        for(int i=0; i<address.length(); i++){
            if (address.charAt(i)=='/' || address.charAt(i)==','||address.charAt(i)=='.' || address.charAt(i)==' ')
                continue;
            int so = address.charAt(i);
            if ((so>=0 && so<=47) ||(so>=58 && so <=64) || (so>90&&so<97) ||(so>122) ){
                return  false;}
        }


        return true;
    }
}
