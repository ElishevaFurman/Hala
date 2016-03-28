package com.example.faigy.hala.Classes;

/**
 * Created by faigy on 3/3/2016.
 */
public class Faqs {
    int id;
    String question, answer;
    public Faqs() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Faqs(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }
}
