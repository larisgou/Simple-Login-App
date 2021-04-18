package com.example.simpleloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;

    private final String Username = "Admin";
    private final String Password = "12345678";

    private boolean isValid = false;

    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eUsername = findViewById(R.id.etUsername);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttempstsInfo);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = eUsername.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if (inputUsername.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all the details correctly!", Toast.LENGTH_SHORT).show();
                } else {
                    isValid = validate(inputUsername, inputPassword);
                    if (!isValid) {
                        counter--;
                        eAttemptsInfo.setText("Attempts Remaining: " + counter);
                        Toast.makeText(MainActivity.this, "Incorrect credentials entered!", Toast.LENGTH_SHORT).show();
                        if (counter == 0){
                            eLogin.setEnabled(false);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean validate(String username, String password){
        if (username.equals(Username) && password.equals(Password)){
            return true;
        }
        return false;
    }
}