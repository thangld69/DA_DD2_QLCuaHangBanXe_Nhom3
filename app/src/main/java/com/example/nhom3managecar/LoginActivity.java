package com.example.nhom3managecar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity  extends AppCompatActivity {

    private EditText username,inputPass;
    private Button login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.edtUsername);
        inputPass = findViewById(R.id.edtPass);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void Login() {
        String email = username.getText().toString();
        String  pass = inputPass.getText().toString();
        if(email.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
        }else if(pass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập PassWord", Toast.LENGTH_SHORT).show();
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    //startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}