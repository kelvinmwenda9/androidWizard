package com.kelvin.dbsql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText username = findViewById(R.id.username3);
        final EditText password = findViewById(R.id.password3);


        final ProgressDialog dialog = new ProgressDialog(login.this);
        dialog.setMax(100);
        dialog.setTitle("Network");
        dialog.setMessage("Please wait login in....");

        Button btnjoin = findViewById(R.id.btnlogin1);
        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();//show dialog
                if (username.getText().toString().length()==0){
                    username.setError("Required!");
                }

                else if (password.getText().toString().length()==0){
                    password.setError("Required!");
                }

                else {

                    AsyncHttpClient asyncHttpClient = new AsyncHttpClient(true, 80, 443);

                    RequestParams requestParams = new RequestParams();
                    requestParams.add("username", username.getText().toString());
                    requestParams.add("password", password.getText().toString());

                    asyncHttpClient.post("https://modkenya.com/kelvin/login.php", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            dialog.dismiss();
                            if (statusCode==200){

                                String res = new String(responseBody);
                                if (res.trim().equalsIgnoreCase("1")){
                                    Intent x = new Intent(login.this, MainActivity.class);
                                    startActivity(x);
                                }

                                else {
                                    Toast.makeText(login.this, "Wrong Credentials" + res, Toast.LENGTH_SHORT).show();
                                    username.setText("");
                                    password.setText("");
                                }
                            }

                            else {
                                Toast.makeText(login.this, "Login not successful"+statusCode, Toast.LENGTH_SHORT).show();
                            }
                            //clear
                            //lo
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            dialog.dismiss();
                            Toast.makeText(login.this, "Got error "+statusCode, Toast.LENGTH_SHORT).show();

                        }
                    });
                    //add internet permission to manifest

                }




            }
        });


        TextView reg = findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(login.this, join.class);
                startActivity(x);
            }
        });

    }
}
