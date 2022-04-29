package com.psit.psitcontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    personAdapter adapter;
    DatabaseReference ubase;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Loading Data ...", Toast.LENGTH_SHORT).show();


        ProgressBar pb = (ProgressBar) findViewById(R.id.idPbLoading);
        pb.setVisibility(ProgressBar.VISIBLE);


        ubase= FirebaseDatabase.getInstance().getReference().child("faculty");

        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<person> options=new FirebaseRecyclerOptions.Builder<person>().setQuery(ubase,person.class).build();
        adapter = new personAdapter(options);
        recyclerView.setAdapter(adapter);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(ProgressBar.INVISIBLE);
            }
        }, 4000);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {

        FirebaseRecyclerOptions<person>options=
                new FirebaseRecyclerOptions.Builder<person>()
                        .setQuery(ubase.orderByChild("fullname").startAt(s.toUpperCase(Locale.ENGLISH)).endAt(s+"\uf8ff"),person.class).build();

        adapter = new personAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}