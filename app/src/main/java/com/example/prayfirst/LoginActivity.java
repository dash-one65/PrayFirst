package com.example.prayfirst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername,txtPassword;
    TextView txtRegister,txtLogin;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        txtUsername = (EditText) findViewById(R.id.idusername);
        txtPassword = (EditText) findViewById(R.id.idpassword);
        txtRegister = (TextView) findViewById(R.id.idregister);
        txtLogin = (TextView) findViewById(R.id.idloginbtn);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUsername.getText().toString().trim();
                String pwd = txtPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if(res == true)
                {
                    Intent LogtoHome = new Intent(LoginActivity.this, LoadingActivity.class);
                    startActivity(LogtoHome);

                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}