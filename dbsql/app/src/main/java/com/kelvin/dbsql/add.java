package com.kelvin.dbsql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText surname = findViewById(R.id.surname);
        final EditText others = findViewById(R.id.others);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById(R.id.phone);
        final EditText nhif = findViewById(R.id.nhif);
        final EditText pin = findViewById(R.id.pin);

        final ProgressDialog dialog = new ProgressDialog(add.this);
        dialog.setMax(100);
        dialog.setTitle("Network");
        dialog.setMessage("Please Wait, Saving your record.");

        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();//show dialog

                AsyncHttpClient asyncHttpClient = new AsyncHttpClient(true, 80, 443);

                if (surname.getText().toString().length()==0){
                    surname.setError("Required");
                }

                else if (others.getText().toString().length()==0){
                    others.setError("Required");
                }

                else if (email.getText().toString().length()==0){
                    email.setError("Required");
                }

                else if (phone.getText().toString().length()==9){
                    phone.setError("Required and Must be 9 numbers");
                }

                else if (nhif.getText().toString().length()==0){
                    nhif.setError("Required");
                }

                else if (pin.getText().toString().length()==0){
                    pin.setError("Required");
                }

                else {

                    RequestParams requestParams = new RequestParams();
                    requestParams.put("surname", surname.getText().toString());
                    requestParams.put("others", others.getText().toString());
                    requestParams.put("email", email.getText().toString());
                    requestParams.put("phone", phone.getText().toString());
                    requestParams.put("nhif", nhif.getText().toString());
                    requestParams.put("pin", pin.getText().toString());

                    asyncHttpClient.post("https://modkenya.com/kelvin/add.php", requestParams,
                            new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    dialog.dismiss();
                                    if (statusCode == 200) {
                                        Toast.makeText(add.this, "Record Submitted " + statusCode, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(add.this, "Record Not Submitted", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    dialog.dismiss();
                                    Toast.makeText(add.this, "Fail " + statusCode, Toast.LENGTH_SHORT).show();
                                }
                            });//end asyncHttpClient.post
                }


            }
        });

    }
}
