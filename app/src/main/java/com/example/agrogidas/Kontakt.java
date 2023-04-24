package com.example.agrogidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Kontakt extends AppCompatActivity {

    private ImageView logo;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.universalusmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent2 = new Intent(Kontakt.this, Informacija.class );
                startActivity(intent2);
                return true;
            case R.id.item2:
                Intent intent = new Intent(Kontakt.this,loginActivity.class );
                startActivity(intent);
                return true;
            case R.id.item3:
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    Intent intent1 = new Intent(Kontakt.this,Account.class );
                    startActivity(intent1);
                } else {
                    Toast.makeText(Kontakt.this, "Neprisijungęs",Toast.LENGTH_SHORT).show();
                }
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontakt);

        //custom image for action bar start
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
                startActivity(new Intent(Kontakt.this,MainActivity.class));

            }
        });




    }
}