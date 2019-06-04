package com.example.pccu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Signin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String account;
    private String password;
    private TextInputLayout accoutLayout;
    private TextInputLayout passwordLayout;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button signinBtn,registeredBtn;
    private FirebaseUser user;
    private RadioGroup radiogroup;
    private RadioButton landlord,student;
    private String landlordId,studentId;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initView();

        //取消ActionBar
        getSupportActionBar().hide();
    }

    private void initView() {
        mAuth = FirebaseAuth.getInstance();
        accountEdit = (EditText) findViewById(R.id.account_edit);
        passwordEdit = (EditText) findViewById(R.id.password_edit);
        accoutLayout = (TextInputLayout) findViewById(R.id.account_layout);
        passwordLayout = (TextInputLayout) findViewById(R.id.password_layout);
        passwordLayout.setErrorEnabled(true);
        accoutLayout.setErrorEnabled(true);
        signinBtn = (Button) findViewById(R.id.signin_btn);
        registeredBtn=(Button) findViewById(R.id.registered_btn);
        landlord=(RadioButton) findViewById(R.id.student_radiobtn);
        student=(RadioButton) findViewById(R.id.student_radiobtn);
        radiogroup=(RadioGroup) findViewById(R.id.radiogroup);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.landlord_radiobtn){
                    final String account = accountEdit.getText().toString();
                    DocumentReference ref=db.collection("landlordinfo").document(account);
                    landlordId=ref.getId();
                    Log.i("回傳ID",landlordId);
                }
                else if(checkedId==R.id.student_radiobtn){
                    final String account = accountEdit.getText().toString();
                    DocumentReference ref=db.collection("landlordinfo").document(account);
                    String studentId=ref.getId();
                    Log.i("回傳ID",studentId);
                }
            }
        });

        //登入驗證
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String account = accountEdit.getText().toString();
                final String password = passwordEdit.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    accoutLayout.setError(getString(R.string.plz_input_accout));
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordLayout.setError(getString(R.string.plz_input_pw));
                    return;
                }
                accoutLayout.setError("");
                passwordLayout.setError("");

                mAuth.signInWithEmailAndPassword(account, password)
                        .addOnCompleteListener(Signin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user = FirebaseAuth.getInstance().getCurrentUser();
                                    boolean emailVerified = user.isEmailVerified();
                                    if (emailVerified == true) {//確認是否驗證信箱
                                        Toast.makeText(Signin.this, R.string.sign_success, Toast.LENGTH_SHORT).show();
                                        if (landlordId.equals(account)) {
                                            Log.i("比對結果", "比對成功");
                                            Intent intent = new Intent();
                                            intent.setClass(Signin.this, landlordsigninsuccess.class);
                                            startActivity(intent);
                                        } else if (studentId.equals(account)) {
                                            Log.i("比對結果", "比對成功");
                                            Intent intent = new Intent();
                                            intent.setClass(Signin.this, StudentSigninSuccess.class);
                                            startActivity(intent);
                                        }
                                    }else {
                                        Toast.makeText(Signin.this, R.string.emailckeck, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Signin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

                registeredBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(Signin.this, Registered.class);
                        startActivity(intent);
                    }
                });
            }


            public void Bt_back(View v) {
                Intent it = new Intent(Signin.this, MainActivity.class);
                startActivity(it);
                finish();
            }
    }
