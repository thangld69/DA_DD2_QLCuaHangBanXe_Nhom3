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
            inputUsername.setError("B???n ch??a nh???p Email!");
        }else  if(pass.isEmpty()) {
            inputPass.setError("B???n ch??a nh???p m???t kh???u!");
        }else  if(pass.length()<6) {
            inputPass.setError("M???t kh???u ph???i ??t nh???t 6 k?? t???!");
        }else if(repass.isEmpty()){//ki???m tra password confrim c?? nh???p hay ch??a
            inputRepass.setError("B???n ch??a x??c nh???n m???t kh???u!");
        }else if(!pass.equals(repass)){//ki???m tra password == password confirm ?
            inputRepass.setError("M???t kh??u x??c nh???n kh??ng ch??nh x??c!");
        }else {
            progressDialog.setMessage("Vui l??ng ?????i...");
            progressDialog.setTitle("Th??ng B??o");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.setMessage("????ng k?? th??nh c??ng");
                        progressDialog.setTitle("Th??ng B??o");
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
