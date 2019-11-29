package model;

public class Account  {
    private Username username;
    private String firstName, lastName;
    private int satScore, actScore;
    private static final int MIN_SAT_SCORE = 400, MAX_SAT_SCORE = 1600, MIN_ACT_SCORE = 1,MAX_ACT_SCORE = 36;
    /*Minimum SAT score is 400 Max SAT score is 1600
     Minimum ACT score is 1 Max ACT score is 36
     Let's make 'em final
     I'm lazy, and don't want to make a filter so if I get passed values outside these limits I set it to max/min*/
    public Account(Username username, String fN, String lN){
        this.username = username;
        this.firstName = fN;
        this.lastName = lN;
    }
    public void setSatScore(int score){
        if(score < MIN_SAT_SCORE){
            this.satScore = MIN_SAT_SCORE;
        }else if(score > MAX_SAT_SCORE){
            this.satScore = MAX_SAT_SCORE;
        }else{
            this.satScore = score;
        }
    }
    public void setActScore(int score){
        if(score < MIN_ACT_SCORE){
            this.actScore = MIN_ACT_SCORE;
        }else if(score > MAX_ACT_SCORE){
            this.actScore = MAX_ACT_SCORE;
        }else{
            this.actScore = score;
        }
    }
    public String getUsername() {return username.getUsername();}
    public String getFirstName() { return firstName;}
    public String getLastName() {return lastName;}
    public int getSatScore() {return satScore;}
    public int getActScore(){return actScore;}





}
