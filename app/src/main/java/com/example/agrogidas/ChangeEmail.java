package com.example.agrogidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ChangeEmail extends AppCompatActivity {

    Button changeEmail;
    EditText oldEmail, newEmail;

    private DatabaseReference reference;

    private String userID;

    private FirebaseUser user;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        changeEmail = findViewById(R.id.changeButE);
        oldEmail = findViewById(R.id.senasE);
        newEmail = findViewById(R.id.naujasE);
        database = FirebaseDatabase.getInstance();

        changeEmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ChangeEmailM();
            }
        });
    }
    private void ChangeEmailM() {
        String userEmail = oldEmail.getText().toString();
        String newwEmail = newEmail.getText().toString();
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Įrašykite seną el. paštą",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(newwEmail )){
            Toast.makeText(this,"Įrašykite naują el. paštą",Toast.LENGTH_SHORT).show();
            return;
        }
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("NormalUsers");
        userID = user.getUid();
        if(!userEmail.equals(newwEmail)){
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UsersM userProfile = snapshot.getValue(UsersM.class);
                    if (userProfile != null)
                    {
                        AuthCredential credential = EmailAuthProvider.getCredential(userEmail, userProfile.passwd);
                        user.reauthenticate(credential)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            user.updateEmail(newwEmail)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()) {
                                                                Toast.makeText(ChangeEmail.this,"El. paštas pakeistas",Toast.LENGTH_SHORT).show();
                                                                UsersM userm2 = new UsersM(userProfile.name, userProfile.sname, newwEmail, userProfile.passwd);
                                                                database.getReferenceFromUrl("https://agrogidas-f3013-default-rtdb.europe-west1.firebasedatabase.app/").child("NormalUsers").child(userID).setValue(userm2);
                                                                startActivity(new Intent(ChangeEmail.this, Account.class));
                                                            }
                                                            else{
                                                                Toast.makeText(ChangeEmail.this,"Nepavyko pakeisti el. pašto!",Toast.LENGTH_SHORT).show();
                                                                return;
                                                            }

                                                        }
                                                    });
                                        }
                                        else{
                                            Toast.makeText(ChangeEmail.this,"Įvestas blogas senas el. paštas",Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                });
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ChangeEmail.this, "Klaida", Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            Toast.makeText(ChangeEmail.this,"Naujas el. paštas atitinka seną",Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
