package com.example.tugasbesar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity115 extends AppCompatActivity {
    private EditText etUsername115, etPassword115;
    private Button btnLogin115, btnRegister115;
    private DatabaseHelper115 dbHelper115;

    @Override
    protected void onCreate(Bundle savedInstanceState115) {
        super.onCreate(savedInstanceState115);
        setContentView(R.layout.activity_login115);

        dbHelper115 = new DatabaseHelper115(this);
        etUsername115 = findViewById(R.id.etUsername115);
        etPassword115 = findViewById(R.id.etPassword115);
        btnLogin115 = findViewById(R.id.btnLogin115);
        btnRegister115 = findViewById(R.id.btnRegister115);

        btnLogin115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v115) {
                String username115 = etUsername115.getText().toString();
                String password115 = etPassword115.getText().toString();
                int userId115 = dbHelper115.checkUser115(username115, password115);
                if (userId115 != -1) {
                    Intent intent115 = new Intent(LoginActivity115.this, MainActivity115.class);
                    intent115.putExtra("USER_ID115", userId115);
                    startActivity(intent115);
                    finish();
                } else {
                    Toast.makeText(LoginActivity115.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v115) {
                String username115 = etUsername115.getText().toString();
                String password115 = etPassword115.getText().toString();
                if (dbHelper115.registerUser115(username115, password115) != -1) {
                    Toast.makeText(LoginActivity115.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity115.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
