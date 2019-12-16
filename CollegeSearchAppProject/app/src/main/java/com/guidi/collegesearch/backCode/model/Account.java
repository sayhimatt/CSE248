package com.guidi.collegesearch.backCode.model;

import com.google.firebase.database.PropertyName;

public class Account {
    private static final int MIN_SAT_SCORE = 200, MAX_SAT_SCORE = 800;
    private Username username;
    private String firstName, lastName;
    private int satMScore, satWScore;

    /*Minimum SAT score is 400 Max SAT score is 1600
     Minimum ACT score is 1 Max ACT score is 36
     Let's make 'em final
     I'm lazy, and don't want to make a filter so if I get passed values outside these limits I set it to max/min*/
    public Account(Username username, String fN, String lN) {
        this.username = username;
        this.firstName = fN;
        this.lastName = lN;
    }

    public Account(Username username, String firstName, String lastName, int satMScore, int satWScore) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        setSatMScore(satMScore);
        setSatWScore(satWScore);
    }

    public String getUsername() {
        return username.getUsername();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSatMScore() {
        return satMScore;
    }
    public int getSatRScore(){
        return satWScore;
    }
    public void setSatMScore(int score) {
        this.satMScore = score;
        if (score < MIN_SAT_SCORE) {
            this.satMScore = MIN_SAT_SCORE;
        } else if (score > MAX_SAT_SCORE)
            this.satMScore = MAX_SAT_SCORE;

    }
    public void setSatWScore(int score) {
        this.satWScore = score;
        if (score < MIN_SAT_SCORE) {
            this.satWScore = MIN_SAT_SCORE;
        } else if (score > MAX_SAT_SCORE)
            this.satWScore = MAX_SAT_SCORE;

    }

    @Override
    public String toString() {
        return "Account{" +
                "username=" + username +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", satMScore=" + satMScore +
                ", satWScore=" + satWScore +
                '}';
    }
}
