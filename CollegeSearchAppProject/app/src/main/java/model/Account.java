package model;

public class Account  {
    private Username uN;
    private Password pW;
    private String firstName, lastName;
    private static final int MIN_SAT_SCORE = 400, MAX_SAT_SCORE = 1600, MIN_ACT_SCORE = 1,MAX_ACT_SCORE = 36;
    /*Minimum SAT score is 400 Max SAT score is 1600
     Minimum ACT score is 1 Max ACT score is 36
     Let's make 'em final
     I'm lazy, and don't want to make a filter so if I get passed values outside these limits I set it to max/min*/
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
