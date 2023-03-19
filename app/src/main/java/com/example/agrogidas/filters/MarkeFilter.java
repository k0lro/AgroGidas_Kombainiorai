package com.example.agrogidas.filters;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrogidas.Kontakt;
import com.example.agrogidas.MainActivity;
import com.example.agrogidas.R;
import com.example.agrogidas.loginActivity;

public class MarkeFilter extends AppCompatActivity {

    private ImageView logo;
    private TextView john;
    private TextView niva;
    private TextView holland;
    private TextView clas;

    private TextView marke;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marke_filter);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //custom image for action bar start
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.home_bar_logo, null);
        getSupportActionBar().setCustomView(view);
        //custom image for action bar end

        logo = findViewById(R.id.logog);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarkeFilter.this, MainActivity.class));

            }
        });

        marke = findViewById(R.id.textView9);

        john = findViewById(R.id.marke1);
        john.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarkeFilter.this, JohnModels.class));
            }
        });

        niva = findViewById(R.id.marke2);
        niva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarkeFilter.this, NivaModels.class));
            }
        });

        holland = findViewById(R.id.marke3);
        holland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarkeFilter.this, NewHollandModels.class));
            }
        });

        clas = findViewById(R.id.marke4);
        clas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarkeFilter.this, ClasModels.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menukontaktai:
                Intent intent = new Intent(MarkeFilter.this, Kontakt.class);
                startActivity(intent);
                return true;

            case R.id.loginActivity:
                Intent intent1 = new Intent(MarkeFilter.this, loginActivity.class);
                startActivity(intent1);

            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}