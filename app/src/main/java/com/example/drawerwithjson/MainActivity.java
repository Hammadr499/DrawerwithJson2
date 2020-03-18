package com.example.drawerwithjson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private NavigationView objectNavigationView;
    private DrawerLayout objectDrawerLayout;

    private View headerView;
    private ImageView profileIV;

    private ActionBarDrawerToggle objectActionBarDrawerToggle;
    private Toolbar objectToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
        test objecttest = new test();
        Gson objectGson = new Gson();
        String obj = objectGson.toJson(objecttest);
        TextView textView = findViewById(R.id.TV1);
        textView.setText(obj);
        String myjson = "{\"test\":{\"maths\":{\"question\":{\"q\":{\"5+1\"},\"list\":\"[\"6\",\"9\",\"10\"]\",\"answer\":{\"6\"}}}}";
        objectGson.fromJson(myjson, test.class);
    }
    public void start(){

        objectNavigationView=findViewById(R.id.navigation_view);
        objectDrawerLayout=findViewById(R.id.DL);
        headerView=objectNavigationView.getHeaderView(0);
        profileIV=headerView.findViewById(R.id.header_IV);
        objectToolbar=findViewById(R.id.toolbar);
        profileIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Profile is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        objectNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if(item.getItemId()==R.id.Profile)
                        {
                            Toast.makeText(MainActivity.this, "profile pic", Toast.LENGTH_SHORT).show();
                            closeMyDrawer();
                            return true;
                        }
                        else if(item.getItemId()==R.id.contact)
                        {
                            Toast.makeText(MainActivity.this, "Contact", Toast.LENGTH_SHORT).show();
                            closeMyDrawer();
                            return true;
                        }
                        return false;
                    }
                }
        );
        setUpHamBurgerIcon();
    }
    private void closeMyDrawer()
    {
        try {
            objectDrawerLayout.closeDrawer(GravityCompat.START);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "closeMyDrawer:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void setUpHamBurgerIcon()
    {
        try
        {
            objectActionBarDrawerToggle=new ActionBarDrawerToggle(
                    this,
                    objectDrawerLayout,objectToolbar,(R.string.open)
                    ,(R.string.close)
            );
            objectActionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(
                    R.color.colorAccent
            ));
            objectActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            objectActionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_add_circle_black_24dp);
            objectActionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    objectDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, "setUpHamBurgerIcon:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
