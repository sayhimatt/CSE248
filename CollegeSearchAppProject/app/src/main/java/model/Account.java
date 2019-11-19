package model;

public class Account  {
    private Username uN;
    private Password pW;
    public Account(Username username, Password password){
        this.uN = username;
        this.pW = password;
    }
    public Username getUsername(){
        return uN;
    }
    public boolean checkPassword(String attemptedP){
        return pW.checkPassword(attemptedP);
    }


}
