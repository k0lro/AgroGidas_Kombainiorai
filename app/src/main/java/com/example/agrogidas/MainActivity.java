package com.example.agrogidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.example.agrogidas.users.Kombainai;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrogidas.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ImageView logo;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Button iksd;
    FirebaseFirestore firestore;

    short_ad_adapter short_ad_adapter;
    List<Kombainai> kombainais;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.










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
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
        //paspaudus logo numeta i pagrindi puslapi

        firestore = FirebaseFirestore.getInstance();

        RecyclerView recyclerView = findViewById(R.id.mainminiskelb);

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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menukontaktai:
                Intent intent = new Intent(MainActivity.this,Kontakt.class );
                startActivity(intent);
                return true;

            case R.id.Paskyra:
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    Intent intent1 = new Intent(MainActivity.this,Account.class );
                    startActivity(intent1);
                } else {
                    Toast.makeText(MainActivity.this, "Neprisijungęs",Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.loginActivity:
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(MainActivity.this,loginActivity.class );
                startActivity(intent2);
                CreatepopUpwindow();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void CreatepopUpwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView=inflater.inflate(R.layout.activity_goodbye,null);
        int width=ViewGroup.LayoutParams.MATCH_PARENT;
        int height=ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable=true;
        PopupWindow popupWindow=new PopupWindow(popUpView,width,height,focusable);
        new Handler().post(new Runnable(){
            @Override
            public void run() {
                popupWindow.showAtLocation(popUpView, Gravity.BOTTOM,0,0);
            }
        });
        TextView gotit;
        gotit=popUpView.findViewById(R.id.gotit);
        gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }








}