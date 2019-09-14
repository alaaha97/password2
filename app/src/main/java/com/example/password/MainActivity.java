package com.example.password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        userName = (TextView) findViewById( R.id.userName );

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences( this );
        String name = sharedPreference.getString( getString( R.string.name_key ), null );
        userName.setText( name );

        if(name == null) {
            showLogInDialog();
        }
    }

    public void showLogInDialog() {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.show( getSupportFragmentManager(), null );
    }
}
