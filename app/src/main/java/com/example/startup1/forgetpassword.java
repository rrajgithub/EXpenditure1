package com.example.startup1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {

    EditText email;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        email = (EditText)findViewById(R.id.email);
        bt = (Button)findViewById(R.id.ChangePassword);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                email = (EditText)findViewById(R.id.email);
                String emails = email.getText().toString();
                firebaseAuth.sendPasswordResetEmail(emails);
                Toast.makeText(forgetpassword.this, "Click On The link Received On Your Registered Account", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(forgetpassword.this, login.class));
                finish();
            }
        });
    }
}
