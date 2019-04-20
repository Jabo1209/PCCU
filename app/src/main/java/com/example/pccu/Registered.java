package com.example.pccu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registered extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String account;
    private String password;
    private TextInputLayout accoutLayout;
    private TextInputLayout passwordLayout;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button signUpBtn;
    private FirebaseUser user;
    private Button verificationEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
        initData();
    }

    public void Bt_back(View v){
        Intent it = new Intent(Registered.this, MainActivity.class);
        startActivity(it);
        finish();
    }

    private void initData() {
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    private void initView() {
        mAuth = FirebaseAuth.getInstance();
        accountEdit = (EditText) findViewById(R.id.account_edit);
        passwordEdit = (EditText) findViewById(R.id.password_edit);
        accoutLayout = (TextInputLayout) findViewById(R.id.account_layout);
        passwordLayout = (TextInputLayout) findViewById(R.id.password_layout);
        passwordLayout.setErrorEnabled(true);
        accoutLayout.setErrorEnabled(true);
        signUpBtn = (Button) findViewById(R.id.signup_button);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(TextUtils.isEmpty(account)){
                    accoutLayout.setError(getString(R.string.plz_input_accout));
                    passwordLayout.setError("");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    accoutLayout.setError("");
                    passwordLayout.setError(getString(R.string.plz_input_pw));
                    return;
                }
                accoutLayout.setError("");
                passwordLayout.setError("");

                mAuth.createUserWithEmailAndPassword(account, password)
                        .addOnCompleteListener(Registered.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Registered.this, R.string.register_success, Toast.LENGTH_SHORT).show();
                                    user.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(Registered.this, "已寄送Email至您的信箱請確認", Toast.LENGTH_SHORT).show();
                                                    } else{
                                                        Toast.makeText(Registered.this, "Email 驗證:"+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                    Intent intent = new Intent();
                                    intent.setClass(Registered.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Registered.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}