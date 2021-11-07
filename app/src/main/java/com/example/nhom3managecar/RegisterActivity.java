package com.example.nhom3managecar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputUsername,inputPass,inputRepass;
    private Button register;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        inputUsername = findViewById(R.id.edtReName);
        inputPass = findViewById(R.id.edtRePass);
        inputRepass = findViewById(R.id.edtReCreatPass);
        register = findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    private void Register() {
        String email = inputUsername.getText().toString();
        String  pass = inputPass.getText().toString();
        String repass = inputRepass.getText().toString();

        if(email.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui long nhap Email!", Toast.LENGTH_SHORT).show();
        }else  if(pass.isEmpty() || repass.length() < 6){
            Toast.makeText(getApplicationContext(), "Vui long nhap hon 6 ky tu!", Toast.LENGTH_SHORT).show();
        }else  if(!pass.equals(repass)){
            Toast.makeText(getApplicationContext(), "Vui long nhap dung Password!", Toast.LENGTH_SHORT).show();
        }else {
            progressDialog.setMessage("Vui long doi...");
            progressDialog.setTitle("Doi");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Dang ky thanh cong!", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
