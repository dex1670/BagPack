package com.example.bagpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.function.IntConsumer;

public class LogInScreen extends AppCompatActivity {

    EditText name;
    EditText password;
    Button login;
    Button signin;
    ImageView imglogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        name = findViewById(R.id.editTextText2);
        password = findViewById(R.id.editTextText3);  // Assuming password field has a different ID
        login = findViewById(R.id.buttonlog);
        signin = findViewById(R.id.buttonsign);
        imglogo = findViewById(R.id.imageView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();  // Get the text from the EditText
                String p = password.getText().toString();

                if (n.isEmpty() || p.isEmpty()) {
                    Toast.makeText(LogInScreen.this, "Enter valid input", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LogInScreen.this, "Create a new account by signing up", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogInScreen.this, "Log in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogInScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInt = new Intent(LogInScreen.this, SignInScreen.class);
                startActivity(signInt);
            }
        });

    }
}