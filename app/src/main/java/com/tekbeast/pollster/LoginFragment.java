package com.tekbeast.pollster;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView RegText;
    private EditText usrname,pwd;
    private Button btn_log;
    OnLoginFormActivityListener loginFormActivityListener;
   public interface OnLoginFormActivityListener{
       public void performRegister();
       public void performLogin(String name);
   }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        RegText = view.findViewById(R.id.register_txt);
        usrname=view.findViewById(R.id.user_name);
        pwd=view.findViewById(R.id.user_pass);
        btn_log=view.findViewById(R.id.login_btn);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });


        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginFormActivityListener.performRegister();


            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        loginFormActivityListener =(OnLoginFormActivityListener) activity;
    }

    private void performLogin(){
       String username,password;
       username=usrname.getText().toString();
       password=pwd.getText().toString();

        Call<User> call =login.apiInterface.performUserLogin(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){

                    login.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());
                }
                else if(response.body().getResponse().equals("failed")){
                    login.prefConfig.displayToast("Login Failed Try Again...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
//                login.prefConfig.displayToast(t.toString());
                Toast.makeText(getActivity(),"Check Internet connection",Toast.LENGTH_SHORT).show();
            }
        });
        usrname.setText("");
        pwd.setText("");
    }




}
