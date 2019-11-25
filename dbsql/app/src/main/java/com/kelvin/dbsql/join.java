package com.kelvin.dbsql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class join extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        final EditText username = findViewById(R.id.username);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);

        final ProgressDialog dialog = new ProgressDialog(join.this);
        dialog.setMax(100);
        dialog.setTitle("Network");
        dialog.setMessage("Please Wait, Saving your record.");

        Button join1 = findViewById(R.id.join1);
        join1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                AsyncHttpClient asyncHttpClient = new AsyncHttpClient(true, 80, 443);

                if (username.getText().toString().length()==0){
                    username.setError("Required");
                }

                else if (email.getText().toString().length()==0){
                    email.setError("Required");
                }

                else if (password.getText().toString().length()==0){
                    password.setError("Required");
                }

                else {
                    RequestParams requestParams = new RequestParams();
                    requestParams.put("username", username.getText().toString());
                    requestParams.put("email", email.getText().toString());
                    requestParams.put("password", password.getText().toString());


                    asyncHttpClient.post("https://modkenya.com/kelvin/join.php", requestParams,
                            new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    dialog.dismiss();
                                    if (statusCode == 200) {
                                        Toast.makeText(join.this, "User Created " + statusCode, Toast.LENGTH_SHORT).show();
                                        username.setText("");
                                        email.setText("");
                                        password.setText("");
                                    } else {
                                        Toast.makeText(join.this, "User not created", Toast.LENGTH_SHORT).show();
                                        username.setText("");
                                        email.setText("");
                                        password.setText("");
                                    }
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    dialog.dismiss();
                                    Toast.makeText(join.this, "Fail " + statusCode, Toast.LENGTH_SHORT).show();
                                    username.setText("");
                                    email.setText("");
                                    password.setText("");
                                }
                            });//end asyncHttpClient.post
                }


            }
        });

        TextView reg = findViewById(R.id.log);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(join.this, login.class);
                startActivity(x);
            }
        });


    }
}
