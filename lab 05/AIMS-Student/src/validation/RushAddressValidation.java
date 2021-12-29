package validation;

public class RushAddressValidation {
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
}
