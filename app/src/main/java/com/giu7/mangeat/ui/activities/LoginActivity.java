package com.giu7.mangeat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.giu7.mangeat.R;
import com.giu7.mangeat.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = LoginActivity.class.getSimpleName();
    private final static String EMAIL_KEY = "email";
    private final static String NUMERO_KEY = "numero";

    private Button loginBtn, registerBtn;
    private EditText mailEt, passwordEt;

    private String mail, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG,"Activity Created");

        mailEt=findViewById(R.id.mail_et);
        passwordEt=findViewById(R.id.password_et);
        loginBtn=findViewById(R.id.login_btn);
        registerBtn=findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        mailEt.addTextChangedListener(loginBtnWatcher);
        passwordEt.addTextChangedListener(loginBtnWatcher);
    }

    private void printToast(@StringRes int resID){
        Toast.makeText(this, getString(resID), Toast.LENGTH_SHORT).show();
    }

    private void doLogin(){

        mail = mailEt.getText().toString();
        password = passwordEt.getText().toString();

        if (!Utils.checkMail(mail)) {
            printToast(R.string.email_error);
            return;
        }
        if (!Utils.checkPassword(password)){
            printToast(R.string.password_error);
            return;
        }

        printToast(R.string.login_ok);
    }

    private TextWatcher loginBtnWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            loginBtn.setEnabled((Utils.checkMail(mailEt.getText().toString()))&&(Utils.checkPassword(passwordEt.getText().toString())));
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.login_btn){
            doLogin();
        }
        else if (v.getId()==R.id.register_btn){
            Intent intentRegister = new Intent (this, RegisterActivity.class);
            startActivity(intentRegister);
        }
    }
}
