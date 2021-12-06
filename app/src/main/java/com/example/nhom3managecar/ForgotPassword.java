package com.example.nhom3managecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    Button btnXacNhan;
    EditText edtEmail;
    private FirebaseAuth auth;
    ProgressDialog progressDialog;
    private static final String TAG = "FirebaseEmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.forgot_password_layout);
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    edtEmail.setError("Bạn chưa nhập email");
                    return;
                }else {
                    progressDialog.setMessage("Vui lòng đợi...");
                    progressDialog.setTitle("Thông Báo");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);//android.R.style.Theme_DeviceDefault
                                builder.setTitle("Thông Báo");
                                builder.setMessage("Reset password thành công vui lòng kiểm tra email");
                                builder.setIcon(R.drawable.ic_question_answer);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        progressDialog.dismiss();
                                        edtEmail.setText("");
                                    }
                                });
                                builder.show();
                                //Toast.makeText(ForgotPassword.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);//android.R.style.Theme_DeviceDefault
                                builder.setTitle("Thông Báo");
                                builder.setMessage("Sai thông tin Email vui lòng kiểm tra lại");
                                builder.setIcon(R.drawable.ic_question_answer);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        progressDialog.dismiss();
                                        edtEmail.setText("");
                                        edtEmail.requestFocus();
                                    }
                                });
                                builder.show();
                            }
                        }
                    });
                }

            }

        });
    }
    private void setControl() {
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        edtEmail = findViewById(R.id.edtEmail);
    }
}