package com.kelvin.farm;

import android.content.Intent;
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


public class add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText username = findViewById(R.id.username1);
        final EditText email = findViewById(R.id.email1);
        final EditText password = findViewById(R.id.password1);
        final EditText phone = findViewById(R.id.phone);
        TextView link = findViewById(R.id.link);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(add.this, login.class);
                startActivity(x);
            }
        });




        Button btnreg = findViewById(R.id.btnreg);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncHttpClient asyncHttpClient = new AsyncHttpClient(true, 80,443);
                if (username.getText().toString().length()==0) {
                    username.setError("Required!");
                }

                else if (email.getText().toString().length()==0){
                    email.setError("Required!");
                }

                else if (password.getText().toString().length()==0){
                    password.setError("Required!");
                }

                else if (phone.getText().toString().length()==0){
                    phone.setError("Required!");
                }

                else {

                    RequestParams requestParams = new RequestParams();
                    requestParams.put("username",username.getText().toString());
                    requestParams.put("email", email.getText().toString());
                    requestParams.put("password", password.getText().toString());
                    requestParams.put("phone", phone.getText().toString());

                    asyncHttpClient.post("https://modkenya.com/kelvin/reg.php", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            if (statusCode==200){
                                Toast.makeText(add.this, "Details Captured "+statusCode, Toast.LENGTH_SHORT).show();
                                password.setText("");
                                email.setText("");
                                phone.setText("");
                                username.setText("");
                            }

                            else {
                                Toast.makeText(add.this, "Record Not Submitted", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            Toast.makeText(add.this, "Failed "+statusCode, Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });//end of listener
    }//end of oncreate bundle
}//end of public class extends
