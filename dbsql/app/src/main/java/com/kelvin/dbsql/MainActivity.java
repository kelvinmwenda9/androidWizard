package com.kelvin.dbsql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Model> datalist;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //find recycler view from content_main.xml
        recyclerView = findViewById(R.id.main_list);
        //set a fixed size
        recyclerView.setHasFixedSize(true);
        //create an arraylist
        datalist = new ArrayList<>();
        //do a layout manager
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        //set layout manager to the recycler view
        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        //create a progress dialog

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMax(100);
        dialog.setTitle("Network");
        dialog.setMessage("Please Wait, retrieving your record.");
        dialog.show();


        AsyncHttpClient asyncHttpClient = new AsyncHttpClient(true, 80, 443);

        RequestParams requestParams = new RequestParams();

        asyncHttpClient.post("https://modkenya.com/kelvin/view.php", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                final Model model = new Model();

                for (int i = 0; i < response.length(); i++) {//for loop

                    try {
                        JSONObject jsonObject = null;

                        try {
                            jsonObject = response.getJSONObject(i);
                            Log.d("mim", "" + response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }//here we have a for loop which loops all items from
                        // the json array into our Model.java

                        model.setSurname(jsonObject.getString("surname"));
                        model.setOthers(jsonObject.getString("others"));
                        model.setEmail(jsonObject.getString("email"));
                        model.setPhone(jsonObject.getString("phone"));
                        model.setNhif(jsonObject.getString("nhif"));
                        model.setPin(jsonObject.getString("pin"));
                        datalist.add(model);//add model to an array list - creating the array

                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                        dialog.dismiss();//dismiss dialog
                    }
                }

                adapter = new Adapter(getApplicationContext(), datalist);
                //put data list to adapter

                recyclerView.setAdapter(adapter);//put adapter to your recycler view

                dialog.dismiss();//dismiss dialog

            }

        }

        );


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(MainActivity.this, add.class);
                startActivity(x);
            }
        });
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
        if (id == R.id.action_settings) {
            Intent x = new Intent(MainActivity.this, join.class);
            startActivity(x);

            //return true;
        }

        if (id == R.id.action_loginUser) {
            Intent x = new Intent(MainActivity.this, login.class);
            startActivity(x);

            //return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
