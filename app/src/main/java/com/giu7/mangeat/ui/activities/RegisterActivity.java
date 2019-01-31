package com.giu7.mangeat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.giu7.mangeat.R;

public class RegisterActivity extends AppCompatActivity {

    EditText nomeEt, cognomeEt, mailET, passwordEt, confPasswordEt;
    Button registerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nomeEt = findViewById(R.id.reg_nome_et);
        cognomeEt = findViewById(R.id.reg_cognome_et);
        mailET = findViewById(R.id.reg_mail_et);
        passwordEt= findViewById(R.id.reg_pass_et);
        confPasswordEt = findViewById(R.id.reg_conf_pass_et);
        registerBtn = findViewById(R.id.register_btn);
    }
}
