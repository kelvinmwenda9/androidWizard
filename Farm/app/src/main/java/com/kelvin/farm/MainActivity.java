package com.kelvin.farm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prf;
    Intent logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        logout = new Intent(MainActivity.this,login.class);


        ImageView shop = findViewById(R.id.shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(MainActivity.this, produce.class);
                startActivity(x);
            }
        });




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(MainActivity.this, UploadImage.class);
                startActivity(x);
            }
        });



    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            SharedPreferences.Editor editor = prf.edit();
            editor.clear();
            editor.commit();
            startActivity(logout);
            finish();


            //return true;
        }

        if (id == R.id.action_help) {
            Toast.makeText(this, "To be updated soon!", Toast.LENGTH_SHORT).show();
        }



        if (id == R.id.action_order) {
            Intent x = new Intent(MainActivity.this, Orders.class);
            startActivity(x);
            Toast.makeText(this, "Research on the tester field is ongoing, To be updated soon!", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.action_post) {
            Intent x = new Intent(MainActivity.this, UploadImage.class);
            startActivity(x);
        }



        if (id == R.id.action_produce) {
            Intent x = new Intent(MainActivity.this, produce.class);
            startActivity(x);
        }

        return super.onOptionsItemSelected(item);
    }
}
