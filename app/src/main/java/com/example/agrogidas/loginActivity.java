package com.example.agrogidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    Button signIn;
    TextView signUp, forgot;
    EditText email, passwd;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.LoginButton);
        signUp = findViewById(R.id.registr);
        forgot = findViewById(R.id.forgot);
        email = findViewById(R.id.email_login);
        passwd = findViewById(R.id.passwd_login);
        signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginUser();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(loginActivity.this,signupActivity.class));
            }
        });
        //ner uz ka btw, zinau as genijus
        forgot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Tavo bėdos - man Kalėdos",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loginUser() {

        String userEmail = email.getText().toString();
        String userPassword = passwd.getText().toString();

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Įrašykite elektroninį paštą",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){

            return;
        }
        //user login
        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginActivity.this, "Prisijungta sėkmingai!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginActivity.this, MainActivity.class));
                        }
                        else{
                            Toast.makeText(loginActivity.this, "Tokios el. pašto ir slaptažodžio kombinacijos nėra"+task.isSuccessful(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}