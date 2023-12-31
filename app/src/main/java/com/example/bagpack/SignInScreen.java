package com.example.bagpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInScreen extends AppCompatActivity {

    EditText name;EditText password;EditText Email;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        name = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextText4);
        Email = findViewById(R.id.editTextText5);
        signup = findViewById(R.id.button2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();  // Get the text from the EditText
                String p = password.getText().toString();
                String e = Email.getText().toString();

                if (n.isEmpty() || p.isEmpty() || e.isEmpty()) {
                    Toast.makeText(SignInScreen.this, "Enter valid input", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignInScreen.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}