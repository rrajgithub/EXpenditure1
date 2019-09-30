package com.example.startup1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button btsignup;
    TextView btsignin, forgetp;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        forgetp = (TextView)findViewById(R.id.forgetpassword);
        btsignup = (Button)findViewById(R.id.signup);
        btsignin = (TextView) findViewById(R.id.login);
        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = email.getText().toString();
                String passwordid = password.getText().toString();
                if( emailid == null || passwordid == null )
                {
                    Toast.makeText(MainActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else if( !( emailid == null && passwordid == null ) )
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(emailid, passwordid).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isComplete())
                            {
                                Toast.makeText(MainActivity.this, "Registration Unsuccessfull, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else if(task.isComplete())
                            {
                                EditText name = (EditText)findViewById(R.id.name);
                                String oname = name.getText().toString();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(oname).build();

                                user.updateProfile(profileUpdates);
                                Toast.makeText(MainActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, home.class));
                               finish();

                            }
                        }
                    });
                }
            }
        });
        btsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, login.class ));
                finish();
            }
        });
        forgetp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, forgetpassword.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser fb = FirebaseAuth.getInstance().getCurrentUser();
        if(!(fb== null))
        {
            startActivity(new Intent(MainActivity.this, home.class));
        }
    }
}
