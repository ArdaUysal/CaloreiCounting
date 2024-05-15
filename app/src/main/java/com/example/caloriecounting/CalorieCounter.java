package com.example.caloriecounting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalorieCounter extends AppCompatActivity {
    Button reset,exit, nextpage, add_apple, add_banana, add_hamburger, add_chicken_wings, add_pizza, add_pasta, add_rice;
    TextView txt_calorie;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calorie_counter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        reset = findViewById(R.id.btn_reset);
        exit = findViewById(R.id.btn_exit);
        nextpage = findViewById(R.id.btn_nextpage);
        add_apple = findViewById(R.id.add);
        add_banana = findViewById(R.id.add1);
        add_hamburger = findViewById(R.id.add2);
        add_chicken_wings = findViewById(R.id.add3);
        add_pizza = findViewById(R.id.add4);
        add_pasta = findViewById(R.id.add5);
        add_rice = findViewById(R.id.add6);
        txt_calorie = findViewById(R.id.calorieinfo);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total_kcal = Integer.parseInt(txt_calorie.getText().toString());
                total_kcal = 0;
                txt_calorie.setText(String.valueOf(total_kcal));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalorieCounter.this,MainActivity.class);
                startActivity(intent);
            }
        });

        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalorieCounter.this,BmiCalculator.class);
                startActivity(intent);
            }
        });


    }
    public void add(View view){
        int id = view.getId();
        int kcal;
        int total_kcal = Integer.parseInt(txt_calorie.getText().toString());;
        if (id == add_apple.getId()){
            kcal = 52;
            total_kcal += kcal;
        } else if(id == add_banana.getId()){
            kcal = 131;
            total_kcal += kcal;
        } else if(id == add_hamburger.getId()){
            kcal = 313;
            total_kcal += kcal;
        } else if(id == add_chicken_wings.getId()){
            kcal = 99;
            total_kcal += kcal;
        } else if(id == add_pizza.getId()){
            kcal = 241;
            total_kcal += kcal;
        } else if(id == add_pasta.getId()){
            kcal = 229;
            total_kcal += kcal;
        } else {
            kcal = 359;
            total_kcal += kcal;
        }
        txt_calorie.setText(String.valueOf(total_kcal));
    }
}