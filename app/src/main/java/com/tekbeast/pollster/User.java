package com.tekbeast.pollster;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Virus on 28-03-2018.
 */

public class User {

    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }
}
