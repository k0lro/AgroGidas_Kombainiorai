package com.example.agrogidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailedittext;
    private ImageView logo;
    private Button resetpassword;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //el pasto nuskaitymas
        emailedittext = (EditText) findViewById(R.id.priminimolaukas);
        //slaptazodzio pakeitimo mygtukas
        resetpassword = (Button) findViewById(R.id.siustimygtukas);
        auth= FirebaseAuth.getInstance();
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        //--------------------BANERIS-------------------------------------------------------
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.home_bar_logo, null);
        getSupportActionBar().setCustomView(view);

        //custom image for action bar end

        logo = findViewById(R.id.logog);
        logo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ForgotPassword.this,loginActivity.class));

            }
        });


    }


    //-----------------------PAGRINDINIS METODAS-----------------------------------------------------
    //Metodas kuris kreipiasi į Firebase, kad atsiųstų slaptažodžio pakeitimo linką
    //Jeigu paštas teisingas, programa palauks dvi sekundes ir nukreips į loginactivity
    private void resetPassword()
    {
        String email= emailedittext.getText().toString();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ForgotPassword.this, "Patikrinkit elektroninį paštą", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    startActivity(new Intent(ForgotPassword.this,loginActivity.class));

                }
                else {

                    Toast.makeText(ForgotPassword.this, "Įvyko problema", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


}