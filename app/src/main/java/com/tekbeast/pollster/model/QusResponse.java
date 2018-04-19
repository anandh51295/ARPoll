package com.tekbeast.pollster.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QusResponse {
    @SerializedName("response")
    private String responses;
    @SerializedName("values")
    private List<Qus> values;

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    public List<Qus> getValues() {
        return values;
    }

    public void setValues(List<Qus> values) {
        this.values = values;
    }
}
