package com.kelvin.farm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;


public class login extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.username2);
        final EditText password = findViewById(R.id.password2);
        Button btnlogin = findViewById(R.id.btnlogin);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);


        TextView link1 = findViewById(R.id.link1);
        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(login.this, add.class);
                startActivity(x);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().length()==0){
                    username.setError("Required");
                }

                else if (password.getText().toString().length()==0){
                    password.setError("Required");
                }

                else {


                    final ProgressDialog dialog = new ProgressDialog(login.this);
                    dialog.setMax(100);
                    dialog.setTitle("Network");
                    dialog.setMessage("Please Wait, retrieving your record.");
                    dialog.show();

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username",username.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.commit();



                    AsyncHttpClient asyncHttpClient = new AsyncHttpClient(true, 80, 443);

                    RequestParams requestParams = new RequestParams();
                    requestParams.put("username", username.getText().toString());
                    requestParams.put("password", password.getText().toString());

                    asyncHttpClient.post("https://modkenya.com/kelvin/login1.php", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (statusCode==200){

                                String res = new String(responseBody);
                                if (res.trim().equalsIgnoreCase("1")){
                                    dialog.dismiss();
                                    Intent x = new Intent(login.this, MainActivity.class);
                                    startActivity(x);
                                }

                                else {
                                    dialog.dismiss();
                                    Toast.makeText(login.this, "Wrong Credentials "+res , Toast.LENGTH_SHORT).show();
                                    username.setText("");
                                    password.setText("");
                                }
                            }

                            else {
                                dialog.dismiss();
                                Toast.makeText(login.this, "Login not Successful"+statusCode, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            Toast.makeText(login.this, "Got Error "+statusCode, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });


    }
}
