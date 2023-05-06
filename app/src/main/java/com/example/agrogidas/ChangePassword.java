package com.example.agrogidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.mindrot.jbcrypt.BCrypt;
import java.util.regex.Pattern;
public class ChangePassword extends AppCompatActivity {
    Button changePass;
    EditText oldPass, newPass;
    private DatabaseReference reference;
    private String userID;

    private FirebaseUser user;

    private FirebaseDatabase database;

    TextView raides8_txt, didzioji_txt, skaicius_txt;

    ImageView raides8_img, didzioji_img, skaicius_img;


    private boolean simb8 = false, didzraid = false, skaic = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        changePass = findViewById(R.id.changeBut);
        oldPass = findViewById(R.id.senas);
        newPass = findViewById(R.id.naujas);
        database = FirebaseDatabase.getInstance();
        changePass.setEnabled(false);

        changePass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ChangePasswd();
            }
        });

        raides8_img = findViewById(R.id.raides8_img4);
        didzioji_img = findViewById(R.id.didzioji_img4);
        skaicius_img = findViewById(R.id.skaicius_img3);

        raides8_txt = findViewById(R.id.raides8_txt4);
        didzioji_txt = findViewById(R.id.didzioji_txt5);
        skaicius_txt = findViewById(R.id.skaicius_txt3);
        newPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pass = newPass.getText().toString();

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
            changePass.setBackgroundColor(getResources().getColor(R.color.teal_900));
            changePass.setEnabled(true);
        }
        else
        {
            changePass.setBackgroundColor(getResources().getColor(R.color.invalid));
            changePass.setEnabled(false);
        }
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
        String hpassword = userPassword;
        String hashedPassword = BCrypt.hashpw(hpassword, BCrypt.gensalt());
        if(!userPassword.equals(newPassword))
        {
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
                                                                UsersM userm = new UsersM(userProfile.name, userProfile.sname, userProfile.email, hashedPassword);
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
        else{
            Toast.makeText(ChangePassword.this,"Naujas slaptažodis atitinka seną",Toast.LENGTH_SHORT).show();
            return;
        }





    }
}