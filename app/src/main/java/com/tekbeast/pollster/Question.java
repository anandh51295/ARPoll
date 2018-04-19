package com.tekbeast.pollster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Question extends AppCompatActivity {

    EditText ques,op_a,op_b,op_c;
    Button btn_sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ques=findViewById(R.id.editTextqus);
        op_a=findViewById(R.id.editTexta);
        op_b=findViewById(R.id.editTextb);
        op_c= findViewById(R.id.editTextc);
        btn_sub=findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Addquestion();
            }
        });
    }

    public void Addquestion(){
        String q,a,b,c,usr;
        usr=login.prefConfig.readName();
        q=ques.getText().toString();
        a=op_a.getText().toString();
        b=op_b.getText().toString();
        c=op_c.getText().toString();

        Call<User> call =login.apiInterface.performAddQuestion(usr,q,a,b,c);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){

//                    login.prefConfig.writeLoginStatus(true);
                    Toast.makeText(getApplicationContext(),"Question added successfully",Toast.LENGTH_LONG).show();

                }
                else if(response.body().getResponse().equals("failed")){
                    Toast.makeText(getApplicationContext(),"Adding Questions Failed Try Again...",Toast.LENGTH_LONG).show();
                }
                else if(response.body().getResponse().equals("exist")){
                    Toast.makeText(getApplicationContext(),"Question Already Exists...",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Check Internet connection",Toast.LENGTH_SHORT).show();

            }
        });
        ques.setText("");
        op_b.setText("");
        op_a.setText("");
        op_c.setText("");

    }

}
