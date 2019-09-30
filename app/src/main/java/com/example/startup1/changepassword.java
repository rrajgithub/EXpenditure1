package com.example.startup1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepassword extends AppCompatActivity {

    EditText npass, cnpass;
    Button chnpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        npass = (EditText)findViewById(R.id.newpassword);
        cnpass = (EditText)findViewById(R.id.confirmnewpassword);
        chnpass = (Button)findViewById(R.id.changepassword);
        chnpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String email = firebaseUser.getEmail();
                if(cnpass.getText().toString().equals(npass.getText().toString())) {
                    String currpass = npass.getText().toString();
                    firebaseUser.updatePassword(currpass);
                    Toast.makeText(changepassword.this, "Password Successfully Changed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(changepassword.this, home.class));
                    finish();
                }
                else if(!(cnpass.getText().toString().equals(npass.getText().toString())))
                {
                    npass.setText("");
                    cnpass.setText("");
                    Toast.makeText(changepassword.this, "Password Does Not Match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
