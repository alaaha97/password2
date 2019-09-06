package com.example.password;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends DialogFragment {

    Button logIn;
    TextInputEditText userName;
    TextInputEditText userPassword;
    TextInputLayout passwordInputLayout;
    CheckBox saveLoginCheckBox;
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    String name = "admin";
    String password = "12345678";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.login_fragment, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );

        logIn = view.findViewById( R.id.login );
        userName = view.findViewById( R.id.userName );
        userPassword = view.findViewById( R.id.userPassword );
        passwordInputLayout = view.findViewById( R.id.passwordInputLayout );
        saveLoginCheckBox = view.findViewById( R.id.check_box );

        userPassword.addTextChangedListener( passwordWatcher );
        logIn.setOnClickListener( logInListener );
    }

    private View.OnClickListener logInListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(!name.equals( userName.getText().toString().trim() ) || !password.equals( userPassword.getText().toString().trim() )) {
                Toast.makeText( getActivity(), "userName and/or Password are not correct", Toast.LENGTH_SHORT ).show();

            } else {
                ((MainActivity) getActivity()).userName.setText( userName.getText().toString() );
                dismiss();

            }

            if(saveLoginCheckBox.isChecked()) {
                sharedPreference = PreferenceManager.getDefaultSharedPreferences( getActivity() );
                editor = sharedPreference.edit();
                editor.putString( "name", userName.getText().toString() );
                editor.apply();
            }
        }
    };

    private TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(charSequence.length() > passwordInputLayout.getCounterMaxLength()) {
                passwordInputLayout.setError( "password is too long" );
            } else {
                passwordInputLayout.setError( null );
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}




