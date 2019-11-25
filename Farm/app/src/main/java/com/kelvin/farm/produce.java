package com.kelvin.farm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class produce extends AppCompatActivity {


    List<DataAdapter> ListOfdataAdapter;

    RecyclerView recyclerView;

    String HTTP_JSON_URL = "https://modkenya.com/kelvin/ImageJsonData.php";

    String Image_Name_JSON = "image_title";

    String Image_URL_JSON = "image_url";

    String Image_Seen_JSON = "seen";

    String Image_Desc_JSON = "descTheAnimal";

    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick;
    ArrayList<String> SeenNameArrayListForClick;
    ArrayList<String> DescTheAnimalNameArrayListForClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create a progress dialog

        final ProgressDialog dialog = new ProgressDialog(produce.this);
        dialog.setMax(100);
        dialog.setTitle("Network");
        dialog.setMessage("Please Wait, retrieving your record.");
        dialog.show();
        dialog.dismiss();

        setContentView(R.layout.produce);

        ImageTitleNameArrayListForClick = new ArrayList<>();
        SeenNameArrayListForClick = new ArrayList<>();
        DescTheAnimalNameArrayListForClick = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        JSON_HTTP_CALL();

        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(produce.this, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);

                    // Showing RecyclerView Clicked Item value using Toast.
                    Toast.makeText(produce.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                    Toast.makeText(produce.this, SeenNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                    Toast.makeText(produce.this, DescTheAnimalNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


    }

    public void JSON_HTTP_CALL(){

        RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ParseJSonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(produce.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setImageTitle(json.getString(Image_Name_JSON));
                GetDataAdapter2.setSeen(json.getString(Image_Seen_JSON));
                GetDataAdapter2.setDescTheAnimal(json.getString(Image_Desc_JSON));

                // Adding image title name in array to display on RecyclerView click event.
                ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                SeenNameArrayListForClick.add(json.getString(Image_Seen_JSON));
                DescTheAnimalNameArrayListForClick.add(json.getString(Image_Desc_JSON));

                GetDataAdapter2.setImageUrl(json.getString(Image_URL_JSON));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            ListOfdataAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }


}