package com.example.password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    public TextView userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mToolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( mToolbar );
        getSupportActionBar().setTitle( "Login " );

        userName = (TextView) findViewById( R.id.userName );
        password = (TextView) findViewById( R.id.password );

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences( this );
        String name = sharedPreference.getString( "name", null );
        userName.setText( name );

        if(name==null){
            Dialog();
        }
    }

    public void Dialog() {
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString( "userName", userName.getText().toString() );
        bundle.putString( "password", password.getText().toString() );
        loginFragment.setArguments( bundle );
        loginFragment.show( getSupportFragmentManager(), null );


    }
}
