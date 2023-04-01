package com.example.agrogidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrogidas.users.UsersM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class signupActivity extends AppCompatActivity {

    TextView prisi, raides8_txt, didzioji_txt, skaicius_txt;
    ImageView raides8_img, didzioji_img, skaicius_img;
    Button regist;
    EditText name, sname, email, passwd;
    FirebaseAuth auth;
    FirebaseDatabase database;
    private boolean simb8 = false, didzraid = false, skaic = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        prisi = findViewById(R.id.prisijung);
        regist = findViewById(R.id.buttonReg);
        name = findViewById(R.id.editTextTextPersonName);
        sname = findViewById(R.id.editTextTextPersonName2);
        email = findViewById(R.id.editTextTextEmailAddress2);
        passwd = findViewById(R.id.editTextTextPassword2);
        regist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createUser();

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

        //SLAPTAZODZIO REIKALAVIMU STUFF
        raides8_img = findViewById(R.id.raides8_img);
        didzioji_img = findViewById(R.id.didzioji_img);
        skaicius_img = findViewById(R.id.skaicius_img);

        raides8_txt = findViewById(R.id.raides8_txt);
        didzioji_txt = findViewById(R.id.didzioji_txt);
        skaicius_txt = findViewById(R.id.skaicius_txt);

        passwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pass = passwd.getText().toString();

                validatePassword(pass);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void validatePassword(String password){
        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern numberCase = Pattern.compile("[0-9]");

        //8 skaiciai
        if(password.length() <= 8)
        {
            raides8_txt.setTextColor(getResources().getColor(R.color.brightGray));
            raides8_img.setColorFilter(getResources().getColor(R.color.brightGray));
            simb8 = false;
        }
        else
        {
            raides8_txt.setTextColor(getResources().getColor(R.color.teal_900));
            raides8_img.setColorFilter(getResources().getColor(R.color.teal_900));
            simb8 = true;
        }

        //didziosiom
        if(!upperCase.matcher(password).find())
        {
            didzioji_txt.setTextColor(getResources().getColor(R.color.brightGray));
            didzioji_img.setColorFilter(getResources().getColor(R.color.brightGray));
            didzraid = false;
        }
        else
        {
            didzioji_txt.setTextColor(getResources().getColor(R.color.teal_900));
            didzioji_img.setColorFilter(getResources().getColor(R.color.teal_900));
            didzraid = true;
        }
        //skaiciam
        if(!numberCase.matcher(password).find())
        {
            skaicius_txt.setTextColor(getResources().getColor(R.color.brightGray));
            skaicius_img.setColorFilter(getResources().getColor(R.color.brightGray));
            skaic = false;
        }
        else
        {
            skaicius_txt.setTextColor(getResources().getColor(R.color.teal_900));
            skaicius_img.setColorFilter(getResources().getColor(R.color.teal_900));
            skaic = true;
        }

    allChecked();
    }

    public void allChecked(){
        if(simb8 && didzraid && skaic)
        {
            regist.setBackgroundColor(getResources().getColor(R.color.teal_900));
        }
        else
        {
            regist.setBackgroundColor(getResources().getColor(R.color.invalid));
        }
    }

    private void createUser() {
        String userName = name.getText().toString();
        String userSName = sname.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = passwd.getText().toString();
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Įrašykite vardą",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userSName)){
            Toast.makeText(this,"Įrašykite pavardę",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Įrašykite elektroninį paštą",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Įrašykite slaptažodį",Toast.LENGTH_SHORT).show();
            return;
        }
        //nauju user'iu sukurimas
        //...............................................
        //
        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            UsersM user = new UsersM(userName, userSName, userEmail, userPassword);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("NormalUsers").child(id).setValue(user);

                            Toast.makeText(signupActivity.this, "Registracija įvykdyta", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signupActivity.this,loginActivity.class));
                        }
                        else{
                            Toast.makeText(signupActivity.this, "Registracija nepavyko" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }


}
