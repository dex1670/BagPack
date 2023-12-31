package com.example.bagpack;

import androidx.appcompat.app.AppCompatActivity;

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

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = String.valueOf(name);
                String p = String.valueOf(password);
                String e = String.valueOf(Email);
                if (n.isEmpty() && p.isEmpty() && e.isEmpty()){
                    Toast.makeText(SignInScreen.this, "Enter Valid input", Toast.LENGTH_SHORT).show();
                }
                {
                    Toast.makeText(SignInScreen.this, "Enter Valid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}