package com.example.caloriecounting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class SignupActivity extends AppCompatActivity {
    EditText txt_username, txt_usermail, txt_userpassword;
    Button btn_signup, btn_login;
    String username,usermail,userpassword;
    DatabaseReference reference;
    FirebaseAuth myauth;
    FirebaseUser myuser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txt_username = findViewById(R.id.txt_username);
        txt_usermail = findViewById(R.id.txt_usermail);
        txt_userpassword = findViewById(R.id.txt_password);
        btn_signup = findViewById(R.id.btn_signup);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txt_username.getText().toString())){
                    txt_username.setError("Lütfen Kullanıcı Adı Kısmını Doldurun!");
                    txt_username.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(txt_usermail.getText().toString())){
                    txt_usermail.setError("Lütfen E-Posta Kısmını Doldurun.");
                    txt_usermail.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(txt_userpassword.getText())){
                    txt_userpassword.setError("Lütfen Parola Kısmını Doldurun.");
                    txt_userpassword.requestFocus();
                    return;
                }
                //Signup();
                Toast.makeText(SignupActivity.this, "Deneme", Toast.LENGTH_SHORT).show();
                usermail = txt_usermail.getText().toString();
                userpassword = txt_userpassword.getText().toString();
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(usermail.trim(),userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Kayıt Başarıyla Tamamlandı Lütfen Tekrar Giriş Yapın.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignupActivity.this, "Kayıt Başarısız.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private void Signup() {
        myauth.createUserWithEmailAndPassword(txt_usermail.getText().toString().trim(),txt_userpassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                 Toast.makeText(SignupActivity.this, "Kayıt Başarıyla Tamamlandı Lütfen Tekrar Giriş Yapın.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, "Kayıt Başarısız.", Toast.LENGTH_SHORT).show();
            }
        });

        /*}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, "Kayıt Başarısız", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}