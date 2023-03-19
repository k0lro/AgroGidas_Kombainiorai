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

public class NewHollandModels extends AppCompatActivity {

    private ImageView logo;

    private TextView pirmas;
    private TextView antras;
    private TextView trecias;
    private TextView ketvirtas;
    private TextView penktas;
    private TextView sestas;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_holland_models);

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
                startActivity(new Intent(NewHollandModels.this, MainActivity.class));

            }
        });

        pirmas = findViewById(R.id.t7s);
        pirmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewHollandModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("T7S");
            }
        });

        antras = findViewById(R.id.t4);
        antras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewHollandModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("T4");
            }
        });

        trecias = findViewById(R.id.t4s);
        trecias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewHollandModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("T4S");
            }
        });

        ketvirtas = findViewById(R.id.boomer);
        ketvirtas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewHollandModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("BOOMER 25C-55");
            }
        });

        penktas = findViewById(R.id.cx5);
        penktas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewHollandModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("CX5");
            }
        });

        sestas = findViewById(R.id.cx6);
        sestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewHollandModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("CX6");
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
                Intent intent = new Intent(NewHollandModels.this, Kontakt.class);
                startActivity(intent);
                return true;

            case R.id.loginActivity:
                Intent intent1 = new Intent(NewHollandModels.this, loginActivity.class);
                startActivity(intent1);

            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}