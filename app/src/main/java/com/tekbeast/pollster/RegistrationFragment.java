package com.tekbeast.pollster;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
    private EditText Username,Password,ConPassword,Email,Number;
    private Button BnRegister;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_registration, container, false);
        Username = view.findViewById(R.id.user);
        Password = view.findViewById(R.id.user_pwd);
        ConPassword = view.findViewById(R.id.user_con_pass);
        Email = view.findViewById(R.id.user_email);
        Number = view.findViewById(R.id.user_number);
        BnRegister=view.findViewById(R.id.register_btn);
        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            PerformRegistration();
            }
        });
        return view;
    }

    public void PerformRegistration(){
        String username,password,conpassword,email,number;
        username=Username.getText().toString();
        password=Password.getText().toString();
        conpassword=ConPassword.getText().toString();
        email=Email.getText().toString();
        number=Number.getText().toString();

        Call<User> call =login.apiInterface.performRegistration(username,email,number,password,conpassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){
                    login.prefConfig.displayToast("Registration success");
                }
                else if(response.body().getResponse().equals("exist")){
                    login.prefConfig.displayToast("User Already exists");
                }
                else if(response.body().getResponse().equals("error")){
                    login.prefConfig.displayToast("Something went Wrong...!");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(),"Check Internet connection",Toast.LENGTH_SHORT).show();

            }
        });
        Username.setText("");
        Email.setText("");

        Number.setText("");
        Password.setText("");

        ConPassword.setText("");
    }

}
