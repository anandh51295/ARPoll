package com.tekbeast.pollster;

import com.tekbeast.pollster.model.QusResponse;
import com.tekbeast.pollster.model.Sel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Virus on 28-03-2018.
 */

public interface ApiInterface {

    @GET("register.php")
    Call<User> performRegistration(@Query("username") String username,@Query("email") String email,@Query("number") String number,@Query("pass") String password,@Query("con_pass") String conpass);

    @GET("login.php")
    Call<User> performUserLogin(@Query("username") String username,@Query("password") String Password);

    @GET("question.php")
    Call<User> performAddQuestion(@Query("usr") String username,@Query("question") String question,@Query("a") String a,@Query("b") String b,@Query("c") String c);

    @GET("fquestion.php")
    Call<QusResponse> performFindQuestion(@Query("usrs") String username);

    @GET("select.php")
    Call<Sel> performOption(@Query("usrs") String username,@Query("question_id") String id);

    @GET("ans.php")
    Call<User> performFR(@Query("usr") String username,@Query("question_id") String id,@Query("answer") String anss);
}
