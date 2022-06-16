package com.moringaschool.adoptpet.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.adoptpet.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.passwordLoginButton)
    Button mPasswordLoginButton;
    @BindView(R.id.emailEditText)
    EditText mEmailEditText;
    @BindView(R.id.passwordEditText)
    EditText mPasswordEditText;
    @BindView(R.id.registerTextView)
    TextView mRegisterTextView;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mRegisterTextView.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mPasswordLoginButton.setOnClickListener(this);

    }
    private void loginWithPassword() {
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        if (email.equals("")) {
            mEmailEditText.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            mPasswordEditText.setError("Password cannot be blank");
            return;
        }
    }
    @Override
    public void onClick(View v) {
        if (v == mRegisterTextView) {
            Intent intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
            finish();
            if (v == mPasswordLoginButton) {
                loginWithPassword();
            }
        }
    }
}