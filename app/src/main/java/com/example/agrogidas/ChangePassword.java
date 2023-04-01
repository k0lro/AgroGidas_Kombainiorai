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

import com.example.agrogidas.users.UsersM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePassword extends AppCompatActivity {
    Button changePass;
    EditText oldPass, newPass;
    private DatabaseReference reference;
    private String userID;

    private FirebaseUser user;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        changePass = findViewById(R.id.changeBut);
        oldPass = findViewById(R.id.senas);
        newPass = findViewById(R.id.naujas);
        database = FirebaseDatabase.getInstance();

        changePass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ChangePasswd();
            }
        });

    }
    private void ChangePasswd() {
        String userPassword = oldPass.getText().toString();
        String newPassword = newPass.getText().toString();
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Įrašykite seną slaptažodį",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(newPassword )){
            Toast.makeText(this,"Įrašykite naują slaptažodį",Toast.LENGTH_SHORT).show();
            return;
        }
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("NormalUsers");
        userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UsersM userProfile = snapshot.getValue(UsersM.class);
                if (userProfile != null)
                {
                    AuthCredential credential = EmailAuthProvider.getCredential(userProfile.email, userPassword);
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        user.updatePassword(newPassword)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()) {
                                                            Toast.makeText(ChangePassword.this,"Slaptažodis pakeistas",Toast.LENGTH_SHORT).show();
                                                            UsersM userm = new UsersM(userProfile.name, userProfile.sname, userProfile.email, newPassword);
                                                            database.getReferenceFromUrl("https://agrogidas-f3013-default-rtdb.europe-west1.firebasedatabase.app/").child("NormalUsers").child(userID).setValue(userm);
                                                            startActivity(new Intent(ChangePassword.this, Account.class));
                                                        }
                                                        else{
                                                            Toast.makeText(ChangePassword.this,"Nepavyko pakeisti slaptažodžio!",Toast.LENGTH_SHORT).show();
                                                            return;
                                                        }
                                                    }
                                                });
                                    }
                                    else{
                                        Toast.makeText(ChangePassword.this,"Įvestas blogas senas slaptažodis",Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                }
                            });
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChangePassword.this, "Klaida", Toast.LENGTH_LONG).show();
            }
        });




    }
}