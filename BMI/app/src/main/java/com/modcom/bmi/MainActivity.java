package com.modcom.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calc = findViewById(R.id.calc);
        final EditText name = findViewById(R.id.name);
        final EditText height = findViewById(R.id.height);
        final EditText weight = findViewById(R.id.weight);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String height1 = height.getText().toString();
                double height2 = Double.parseDouble(height1);
                String weight1 = weight.getText().toString();
                double weight2 = Double.parseDouble(weight1);

                double bmi = weight2/(height2*height2);

                if (bmi<18.5){
                    Toast.makeText(MainActivity.this, name1+" \nYour BMI IS: "+bmi+"\nYou are Underweight", Toast.LENGTH_SHORT).show();
                }

                else if (bmi>18.4 && bmi<23){
                    Toast.makeText(MainActivity.this, name1+" \nYour BMI IS: "+bmi+"\nYou are Normal", Toast.LENGTH_SHORT).show();

                }

                else {
                    Toast.makeText(MainActivity.this, name1+" \nYour BMI IS: "+bmi+"\nYou are Obese", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}
