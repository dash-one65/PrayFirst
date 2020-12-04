package com.example.prayfirst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText txtUsername,txtPassword,txtConPassword;
    TextView txtRegister,txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        txtUsername = (EditText) findViewById(R.id.idusername);
        txtPassword = (EditText) findViewById(R.id.idpassword);
        txtConPassword = (EditText) findViewById(R.id.idconpassword);
        txtLogin = (TextView) findViewById(R.id.idlogin);
        txtRegister = (TextView) findViewById(R.id.idregisterbtn);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUsername.getText().toString().trim();
                String pwd = txtPassword.getText().toString().trim();
                String cnf = txtConPassword.getText().toString().trim();

                if (pwd.equals(cnf)){
                    long val = db.addUser(user,pwd);
                    if(val > 0) {
                        Toast.makeText(RegisterActivity.this, "You have Registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(moveToLogin);
                    }else
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Passwrod is not matching",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}