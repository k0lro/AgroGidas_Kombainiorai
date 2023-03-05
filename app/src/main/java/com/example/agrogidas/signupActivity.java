package com.example.agrogidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class signupActivity extends AppCompatActivity {

    TextView prisi;
    Button regist;

    EditText vardas, pavarde, email, slapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        prisi = findViewById(R.id.prisijung);
        regist = findViewById(R.id.buttonReg);
        vardas = findViewById(R.id.editTextTextPersonName);
        pavarde = findViewById(R.id.editTextTextPersonName2);
        email = findViewById(R.id.editTextTextEmailAddress2);
        slapt = findViewById(R.id.editTextTextPassword2);
        regist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(signupActivity.this,loginActivity.class));

            }
        });
        prisi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(signupActivity.this,loginActivity.class));
            }
        });
    }
}