package com.example.fad.beninquiz;

/**
 * Created by Crepin Hugues FADJO (f@dcrepin) on 19/08/2016.
 */
public class Question {
    private int pictureID;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String correctAnswer;
    private String questionText;
    private boolean creditAlreadyGiven;

    public Question() {
    }

    public Question( int pictureID,String questionText, String choiceA, String choiceB, String choiceC, String correctAnswer) {
        this.questionText = questionText;
        this.pictureID = pictureID;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.correctAnswer = correctAnswer;
        this.creditAlreadyGiven=false;
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isCreditAlreadyGiven() {
        return creditAlreadyGiven;
    }

    public void setCreditAlreadyGiven(boolean creditAlreadyGiven) {
        this.creditAlreadyGiven = creditAlreadyGiven;
    }

    public boolean isCorrectAnswer(String selectedAnswer){

        return (selectedAnswer.equals(correctAnswer));
    }
}
