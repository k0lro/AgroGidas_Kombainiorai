package com.example.agrogidas;

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

import com.example.agrogidas.filters.ClasModels;

public class MetaiFilter extends AppCompatActivity {

    private ImageView logo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metai_filter);

        setContentView(R.layout.clas_models);
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
                startActivity(new Intent(MetaiFilter.this, MainActivity.class));

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
                Intent intent = new Intent(MetaiFilter.this, Kontakt.class);
                startActivity(intent);
                return true;

            case R.id.loginActivity:
                Intent intent1 = new Intent(MetaiFilter.this, loginActivity.class);
                startActivity(intent1);

            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}