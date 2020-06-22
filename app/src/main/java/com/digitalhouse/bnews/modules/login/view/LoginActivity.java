package com.digitalhouse.bnews.modules.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.digitalhouse.bnews.R;
import com.digitalhouse.bnews.modules.login.viewmodel.LoginViewModel;
import com.digitalhouse.bnews.modules.signIn.view.SignInActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private Button loginButton;
    private TextView signIn;
    private LoginViewModel loginViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.login_usuario_edit_text);
        passwordEditText = findViewById(R.id.login_senha_edit_text);
        loginButton = findViewById(R.id.login_button_ok_id);
        signIn = findViewById(R.id.sign_in_text_view);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goesToSignIn();
            }
        });
    }

    private void logar() {

        String inputEmail = emailEditText.getEditableText().toString();
        String inputPassword = passwordEditText.getEditableText().toString();

        emailEditText.setError(null);
        emailEditText.setError(null);

        loginViewModel.authenticateUser(inputEmail, inputPassword);

//        if (inputEmail.equals("teste@evino.com") && inputPassword.equals("123456")) {
//
//            Intent intent = new Intent(this, NoticiaActivity.class);
//            startActivity(intent);
//            finish();
//
//        } else {
//            emailEditText.setError("usuario e/ou senha incorretos");
//            senhaEditText.setError("usuario e/ou senha incorretos");
//        }
    }


    private void goesToSignIn() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}
