package com.example.agrogidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrogidas.users.UsersM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signupActivity extends AppCompatActivity {

    TextView prisi;
    Button regist;
    EditText name, sname, email, passwd;
    FirebaseAuth auth;
    FirebaseDatabase database;

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
        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            UsersM user = new UsersM(userName, userSName, userEmail, userPassword);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Admins").child(id).setValue(user);

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
