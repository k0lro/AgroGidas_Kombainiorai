package com.example.agrogidas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.agrogidas.users.Kombainai;

public class full_ad extends AppCompatActivity {

    private ImageView logo;

    TextView markeid, modelisid, kainaid, numerisid, miestasid, dataid, galiaid, darbinis_plotisid,
            motovalandosid, aprasymasid;
    ImageView pagrindine_foto;
    Kombainai kombainai = null;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_ad);

        //custom image for action bar start
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.home_bar_logo, null);
        getSupportActionBar().setCustomView(view);
        //custom image for action bar end

        //paspaudus logo numeta i pagrindi puslapi
        logo = findViewById(R.id.logog);
        logo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(full_ad.this,short_ad_activity.class));
            }
        });

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof Kombainai){
            kombainai = (Kombainai) object;
        }

        pagrindine_foto = findViewById(R.id.pagrindine_foto);
        markeid = findViewById(R.id.markeid);
        modelisid = findViewById(R.id.modelisid);
        kainaid = findViewById(R.id.kainaid);
        numerisid = findViewById(R.id.numerisid);
        miestasid = findViewById(R.id.miestasid);
        dataid = findViewById(R.id.dataid);
        galiaid = findViewById(R.id.galiaid);
        darbinis_plotisid = findViewById(R.id.darbinis_plotisid);
        motovalandosid = findViewById(R.id.motovalandosid);
        aprasymasid = findViewById(R.id.aprasymasid);

        if(kombainai != null)
        {
            Glide.with(getApplicationContext()).load(kombainai.getNuotrauka()).into(pagrindine_foto);
            markeid.setText(kombainai.getMarke());
            modelisid.setText(kombainai.getModelis());
            kainaid.setText(String.valueOf(kombainai.getKaina() + " €"));
            numerisid.setText(String.valueOf(kombainai.getTelefonas()));
            miestasid.setText(kombainai.getMiestas());
            dataid.setText(String.valueOf(kombainai.getMetai()));
            galiaid.setText(String.valueOf(kombainai.getGalia()));
            darbinis_plotisid.setText(String.valueOf(kombainai.getDarbinis_plotis()));
            motovalandosid.setText(String.valueOf(kombainai.getMotovalandos()));
            aprasymasid.setText(kombainai.getAprasas());
        }
    }
}