package com.example.nhom3managecar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputUsername,inputPass,inputRepass;
    private Button register;
    ProgressDialog progressDialog;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.register_activity);

        inputUsername = findViewById(R.id.edtEmail);
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
            inputUsername.setError("Bạn chưa nhập Email!");
        }else  if(pass.isEmpty()) {
            inputPass.setError("Bạn chưa nhập mật khẩu!");
        }else  if(pass.length()<6) {
            inputPass.setError("Mật khẩu phải ít nhất 6 kí tự!");
        }else if(repass.isEmpty()){//kiểm tra password confrim có nhập hay chưa
            inputRepass.setError("Bạn chưa xác nhận mật khẩu!");
        }else if(!pass.equals(repass)){//kiểm tra password == password confirm ?
            inputRepass.setError("Mật khâu xác nhận không chính xác!");
        }else {
            progressDialog.setMessage("Vui lòng đợi...");
            progressDialog.setTitle("Thông Báo");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.setMessage("Đăng ký thành công");
                        progressDialog.setTitle("Thông Báo");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);

                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
}
