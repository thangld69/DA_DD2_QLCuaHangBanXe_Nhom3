package com.example.nhom3managecar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity  extends AppCompatActivity {

    private EditText username,inputPass;
    private Button login;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
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

    @Override
    public void onBackPressed() {
        //neu drawer da dong thi thoat app
        progressDialog.dismiss();
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private void Login() {
        String email = username.getText().toString();
        String  pass = inputPass.getText().toString();
        if(email.isEmpty()){
            username.setError("Bạn chưa nhập Email!");
        }else if(pass.isEmpty()){
            inputPass.setError("Bạn chưa nhập password!");
        }else {
            progressDialog.setMessage("Vui lòng đợi...");
            progressDialog.setTitle("Thông Báo");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.setMessage("Đăng nhập thành công");
                        progressDialog.setTitle("Thông Báo");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);//android.R.style.Theme_DeviceDefault
                        builder.setTitle("Đăng nhập thất bại");
                        builder.setMessage("Vui lòng kiểm tra lại email và password");
                        builder.setIcon(R.drawable.ic_question_answer);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                inputPass.setText("");
                                username.requestFocus();
                                progressDialog.dismiss();
                            }
                        });
                        builder.show();
                    }
                }
            });
        }

    }
}