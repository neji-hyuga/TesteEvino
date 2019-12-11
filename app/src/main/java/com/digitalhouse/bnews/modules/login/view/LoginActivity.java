package com.digitalhouse.bnews.modules.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.digitalhouse.bnews.R;
import com.digitalhouse.bnews.modules.noticia.view.NoticiaActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.login_usuario_edit_text);
        senhaEditText = findViewById(R.id.login_senha_edit_text);
        loginButton = findViewById(R.id.login_button_ok_id);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });
    }

    private void logar() {

        String emailDigitado = emailEditText.getEditableText().toString();
        String senhaDigitada = senhaEditText.getEditableText().toString();

        emailEditText.setError(null);
        emailEditText.setError(null);

        if (emailDigitado.equals("teste@bnews.com") && senhaDigitada.equals("123456")) {

            Intent intent = new Intent(this, NoticiaActivity.class);
            startActivity(intent);
            finish();

        } else {
            emailEditText.setError("usuario e/ou senha incorretos");
            senhaEditText.setError("usuario e/ou senha incorretos");
        }
    }
}
