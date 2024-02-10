package com.example.notetaskpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    EditText id, date, title, desc;
    MyAdapter helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NoteFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_notes);
        }

//        //setContentView(R.layout.note_recycler);
//        // get the reference of RecyclerView
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        // set a LinearLayoutManager with default vertical orientation
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        //  call the constructor of CustomAdapter to send the reference and data to Adapter
//        NoteAdapter customAdapter = new NoteAdapter(MainActivity.this, noteNames);
//        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_notes)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NoteFragment()).commit();

        if(id == R.id.new_note)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddNote()).commit();

        if(id == R.id.nav_todo)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ToDoFragment()).commit();


        if(id == R.id.nav_settings)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();


        if(id == R.id.nav_about)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();



        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }






}