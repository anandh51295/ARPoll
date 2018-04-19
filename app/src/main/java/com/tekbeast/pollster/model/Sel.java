package com.tekbeast.pollster.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Sel {

    @SerializedName("response")
    private String response;
    @SerializedName("a")
    private String a;
    @SerializedName("b")
    private String b;
    @SerializedName("c")
    private String c;

    public String getResponse() {
        return response;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }
}
