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

import org.w3c.dom.Text;

public class JohnModels extends AppCompatActivity {

    private ImageView logo;

    private TextView pirmas;
    private TextView antras;
    private TextView trecias;
    private TextView ketvirtas;
    private TextView penktas;
    private TextView sestas;

    private TextView modelis;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.john_models);

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
                startActivity(new Intent(JohnModels.this, MainActivity.class));

            }
        });

        pirmas = findViewById(R.id.s760);
        pirmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JohnModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("S760");
            }
        });

        antras = findViewById(R.id.s770);
        antras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JohnModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("S770");
            }
        });

        trecias = findViewById(R.id.s780);
        trecias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JohnModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("S780");
            }
        });

        ketvirtas = findViewById(R.id.s790);
        ketvirtas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JohnModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("S790");
            }
        });

        penktas = findViewById(R.id.x91);
        penktas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JohnModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("X9 1000");
            }
        });

        sestas = findViewById(R.id.x911);
        sestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JohnModels.this, MainActivity.class));
                final TextView model = (TextView) findViewById(R.id.textView10);
                model.setText("X9 1100");
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
                Intent intent = new Intent(JohnModels.this, Kontakt.class);
                startActivity(intent);
                return true;

            case R.id.loginActivity:
                Intent intent1 = new Intent(JohnModels.this, loginActivity.class);
                startActivity(intent1);

            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}