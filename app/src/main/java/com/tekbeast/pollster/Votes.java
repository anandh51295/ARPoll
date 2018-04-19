package com.tekbeast.pollster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tekbeast.pollster.adapter.QusAdapter;
import com.tekbeast.pollster.model.Qus;
import com.tekbeast.pollster.model.QusResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Votes extends AppCompatActivity {


    public static ApiInterface apiInterface;
    private static final String TAG = Votes.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.qus_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String usr=login.prefConfig.readName();

        Call<QusResponse> call = apiInterface.performFindQuestion(usr);
        call.enqueue(new Callback<QusResponse>() {
            @Override
            public void onResponse(Call<QusResponse> call, Response<QusResponse> response) {

                    if(response.body().equals("failed")){
                       Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_SHORT).show();
                    }
                    else{

                    List<Qus> question = response.body().getValues();
                   Log.d(TAG, "Number of Questions received: " + question.size());
                    recyclerView.setAdapter(new QusAdapter(question, R.layout.item_layout, getApplicationContext()));}




            }

            @Override
            public void onFailure(Call<QusResponse> call, Throwable t) {
//                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(),"Check Internet connection",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
