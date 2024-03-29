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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.mindrot.jbcrypt.BCrypt;

public class loginActivity extends AppCompatActivity {

    Button signIn;
    TextView signUp, forgot;
    EditText email, passwd;
    FirebaseAuth auth;

    private FirebaseDatabase database;
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

        //Mygtukas "pramiršote slaptažodį" nukreipia į naują puslapį
        //Forgot Password
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this, ForgotPassword.class));

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
        if(TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Įrašykite slaptažodį", Toast.LENGTH_SHORT).show();
            return;
        }
        //user login
        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Pridėtas el pašto tvirtinimas. Jeigu jis nėra patvirtintas, išmetama error žinutė
                            //Jeigu viskas sėkminga, naudotojas nukreipiamas į pagrindinį puslapį
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if(user.isEmailVerified()){
                            Toast.makeText(loginActivity.this, "Prisijungta sėkmingai!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginActivity.this, MainActivity.class));}
                            else {
                                Toast.makeText(loginActivity.this, "Nepatvirtintas elektroninis paštas",Toast.LENGTH_LONG).show();
                                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();

                            }
                        }
                        else{
                            Toast.makeText(loginActivity.this, "Tokios el. pašto ir slaptažodžio kombinacijos nėra",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



}