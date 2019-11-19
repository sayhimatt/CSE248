package model;

public class Password {
    String password;
    public Password(String p){
        this.password = p;
    }
    public boolean checkPassword(String p){
        if(password.compareTo(p) == 0)
            return true;
        return false;
    }
}
