package com.tekbeast.pollster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tekbeast.pollster.model.Sel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Selects extends AppCompatActivity {

    String iid,qquestions,a,b,c,usr,ee,fin;
    Button finish;
    RadioButton aa,bb,cc;
    TextView hint,questions;
    RadioGroup rr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selects);
        usr=login.prefConfig.readName();
        iid=getIntent().getStringExtra("id");
        qquestions=getIntent().getStringExtra("question");
        questions=findViewById(R.id.setQus);
        hint=findViewById(R.id.found);
        finish=findViewById(R.id.btn_finish);
        aa=findViewById(R.id.aa);
        bb=findViewById(R.id.bb);
        cc=findViewById(R.id.cc);
       rr = findViewById(R.id.rgroup);
        questions.setText(qquestions);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton rb = (RadioButton) rr.findViewById(rr.getCheckedRadioButtonId());
                Call<User> call =login.apiInterface.performFR(usr,iid,rb.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body().getResponse().equals("ok")){


                            finish();
                            Toast.makeText(getApplicationContext(),"Answer submitted Successfully...",Toast.LENGTH_LONG).show();

                        }
                        else if(response.body().getResponse().equals("exist")){
                            Toast.makeText(getApplicationContext()," Already Exists...",Toast.LENGTH_LONG).show();
                        }
                        else if(response.body().getResponse().equals("failed")){
                            Toast.makeText(getApplicationContext(),"Try again Later...",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Check Internet connection",Toast.LENGTH_SHORT).show();

                    }
                });
//                Toast.makeText(Selects.this, rb.getText(), Toast.LENGTH_SHORT).show();


            }
        });
//        Toast.makeText(getApplicationContext(),iid+""+qquestions,Toast.LENGTH_LONG).show();






        Call<Sel> call =login.apiInterface.performOption(usr,iid);
        call.enqueue(new Callback<Sel>() {
            @Override
            public void onResponse(Call<Sel> call, Response<Sel> response) {
                if(response.body().getResponse().equals("ok")){
                    a=response.body().getA();
                    b=response.body().getB();
                    c=response.body().getC();
                    aa.setText(a);
                    bb.setText(b);
                    cc.setText(c);
//                    login.prefConfig.writeLoginStatus(true);
                    //Toast.makeText(getApplicationContext(),"Question added successfully",Toast.LENGTH_LONG).show();

                }
                else if(response.body().getResponse().equals("already")){

                    ee=response.body().getA();
                    hint.setText("Your answer for this Question: "+ee);
                    if(hint.getText().equals("")||hint.getText().equals(null)){
                        hint.setVisibility(View.INVISIBLE);
                        aa.setVisibility(View.VISIBLE);
                        bb.setVisibility(View.VISIBLE);
                        cc.setVisibility(View.VISIBLE);
                        rr.setVisibility(View.VISIBLE);
                        finish.setVisibility(View.VISIBLE);
                    }
                    else{
                        hint.setVisibility(View.VISIBLE);
                        aa.setVisibility(View.INVISIBLE);
                        bb.setVisibility(View.INVISIBLE);
                        cc.setVisibility(View.INVISIBLE);
                        rr.setVisibility(View.INVISIBLE);
                        finish.setVisibility(View.INVISIBLE);
                    }

//                    Toast.makeText(getApplicationContext(),"Already answered...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Sel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Check Internet connection",Toast.LENGTH_SHORT).show();
            }

        });


    }
}
