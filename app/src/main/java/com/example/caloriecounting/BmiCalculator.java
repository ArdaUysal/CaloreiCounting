package com.example.caloriecounting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class BmiCalculator extends AppCompatActivity {
    EditText userheight, userweight;
    Button btn_previous_page, btn_calculate;
    TextView txt_bmi, txt_bmi_info;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userheight = findViewById(R.id.txt_height);
        userweight = findViewById(R.id.txt_weight);
        btn_previous_page = findViewById(R.id.btn_previouspage);
        btn_calculate = findViewById(R.id.btn_calculate);
        txt_bmi = findViewById(R.id.txt_bmi);
        txt_bmi_info = findViewById(R.id.bmi_info);

        btn_previous_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiCalculator.this,CalorieCounter.class);
                startActivity(intent);
            }
        });

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(userheight.getText().toString().trim())){
                    userheight.setError("Lütfen Boy(metre) Bilginizi Girin!");
                    userheight.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(userweight.getText().toString().trim())){
                    userweight.setError("Lütfen Kilo(kg) Bilginizi Girin!");
                    userweight.requestFocus();
                    return;
                }
                double height = Double.parseDouble(userheight.getText().toString());
                double weight = Double.parseDouble(userweight.getText().toString());
                double bmi = weight /(height * height);

                if(bmi < 18.5){
                    txt_bmi_info.setText("Zayıf.");
                } else if(bmi >= 18.5 && bmi < 25){
                    txt_bmi_info.setText("Normal.");
                } else if(bmi >= 25 && bmi < 29.9){
                    txt_bmi_info.setText("Fazla Kilolu.");
                } else {
                    txt_bmi_info.setText("Obezite.");
                }


                DecimalFormat df = new DecimalFormat("#.#");
                String formattedBMI = df.format(bmi);
                txt_bmi.setText(formattedBMI);

            }
        });
    }
}