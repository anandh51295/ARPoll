package com.tekbeast.pollster.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Qus {
    @SerializedName("id")
    private String id;
    @SerializedName("question")
    private String question;

    public Qus(String id, String question) {

        this.id = id;
        this.question=question;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
