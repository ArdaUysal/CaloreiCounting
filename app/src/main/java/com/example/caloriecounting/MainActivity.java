package com.example.caloriecounting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText txt_usermail,txt_userpassword;
    Button btn_signup,btn_login;
    String usermail,userpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_usermail = findViewById(R.id.txt_usermail);
        txt_userpassword = findViewById(R.id.txt_userpassword);
        btn_signup = findViewById(R.id.btn_signup);
        btn_login = findViewById(R.id.btn_login);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txt_usermail.getText().toString())){
                    txt_usermail.setError("Lütfen E-Posta Kısmını Doldurun.");
                    txt_usermail.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(txt_userpassword.getText().toString())){
                    txt_userpassword.setError("Lütfen Şifre Kısmını Doldurun.");
                    txt_userpassword.requestFocus();
                    return;
                }
                usermail = txt_usermail.getText().toString();
                userpassword = txt_userpassword.getText().toString();
                FirebaseAuth.getInstance().signInWithEmailAndPassword(usermail.trim(),userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Giriş başarılı.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,CalorieCounter.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Giriş başarısız.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
    }
}