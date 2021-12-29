package validation;

public class NameValidation {

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
}
