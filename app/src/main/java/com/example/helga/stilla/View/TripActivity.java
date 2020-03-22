package com.example.helga.stilla.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.helga.stilla.R;
import com.example.helga.stilla.Room.Entity.LoginTable;
import com.example.helga.stilla.ViewModel.LoginViewModel;
import com.example.helga.stilla.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Objects;



public class TripActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityMainBinding  binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        /*Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String pass = extras.getString("USER_PASS");
        String mail = extras.getString("USER_MAIL");

        TextView passView = findViewById(R.id.pass);
        TextView mailView = findViewById(R.id.mail);

        passView.setText(pass);
        mailView.setText(mail);*/
    }

    public void log(View view) {
        Log.d("helga: ", "halló onclick í hinu");

        //LoginDatabase db = Room.databaseBuilder(getApplicationContext(), LoginDatabase.class, "LOGIN_DATABASE").build();

        //LiveData<List<LoginTable>> loginTable = db.loginDoa().getDetails();

        //loginTable.getValue().

        // TODO: svona er hægt að skoða gögn allstaðar í appinu!!!
        loginViewModel = ViewModelProviders.of(TripActivity.this).get(LoginViewModel.class);
        loginViewModel.getGetAllData().observe(this, new Observer<List<LoginTable>>() {
            @Override
            public void onChanged(@Nullable List<LoginTable> data) {

                String pass = Objects.requireNonNull(data).get(0).getPassword();
                String mail = Objects.requireNonNull(data).get(0).getEmail();

                TextView passView = findViewById(R.id.pass);
                TextView mailView = findViewById(R.id.mail);

                passView.setText(pass);
                mailView.setText(mail);
            }
        });
    }
}
