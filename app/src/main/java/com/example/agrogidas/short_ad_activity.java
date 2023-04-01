package com.example.agrogidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.agrogidas.users.Kombainai;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class short_ad_activity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    short_ad_adapter short_ad_adapter;
    List<Kombainai> kombainais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_ad2);


        firestore = FirebaseFirestore.getInstance();
        //String marke = getIntent().getStringExtra("marke");
        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        kombainais = new ArrayList<>();
        short_ad_adapter = new short_ad_adapter(this,kombainais);
        recyclerView.setAdapter(short_ad_adapter);
        firestore.collection("Kombainai").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                    Kombainai kombainai = documentSnapshot.toObject(Kombainai.class);
                    kombainais.add(kombainai);
                    short_ad_adapter.notifyDataSetChanged();
                }
            }
        });
    }
}