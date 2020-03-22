package com.example.helga.stilla.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.helga.stilla.DataBinding.Listener;
import com.example.helga.stilla.R;
import com.example.helga.stilla.Room.Entity.LoginTable;
import com.example.helga.stilla.Utilities.GlobalVariables;
import com.example.helga.stilla.ViewModel.LoginViewModel;
import com.example.helga.stilla.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Listener {

    private LoginViewModel loginViewModel;
    private ActivityMainBinding binding;
    private GlobalVariables mGlobalVariables = new GlobalVariables();


    public GlobalVariables getGlobalVariables() {
        return mGlobalVariables;
    }

    public void setGlobalVariables(GlobalVariables globalVariables) {
        mGlobalVariables = globalVariables;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginActivity();

    }

    private void LoginActivity() {
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setClickListener((MainActivity) this);


        loginViewModel = ViewModelProviders.of(MainActivity.this).get(LoginViewModel.class);

        loginViewModel.getGetAllData().observe(this, new Observer<List<LoginTable>>() {
            @Override
            public void onChanged(@Nullable List<LoginTable> data) {

                try {
                    binding.lblEmailAnswer.setText((Objects.requireNonNull(data).get(0).getEmail()));
                    binding.lblPasswordAnswer.setText((Objects.requireNonNull(data.get(0).getPassword())));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {

        String strEmail = binding.txtEmailAddress.getText().toString().trim();
        String strPassword = binding.txtPassword.getText().toString().trim();
        LoginTable data = new LoginTable();

        mGlobalVariables.setLoginTable(data);

        if (TextUtils.isEmpty(strEmail)) {
            binding.txtEmailAddress.setError("Please Enter Your E-mail Address");
        }
        else if (TextUtils.isEmpty(strPassword)) {
            binding.txtPassword.setError("Please Enter Your Password");
        }
        else {

            data.setEmail(strEmail);
            data.setPassword(strPassword);
            loginViewModel.insert(data);

            Intent intent = new Intent(this, TripActivity.class);
            Bundle extras = new Bundle();
            extras.putString("USER_MAIL", data.getEmail());
            extras.putString("USER_PASS", data.getPassword());
            intent.putExtras(extras);
            startActivity(intent);
        }

        loginViewModel.numEntries().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Log.d("numEntries: ", String.valueOf(integer));
            }
        });


    }
}
