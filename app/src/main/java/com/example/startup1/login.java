package com.example.startup1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class login extends AppCompatActivity {

    EditText email, password;
    Button btsignin;
    TextView btsignup;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaselistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        btsignin = (Button)findViewById(R.id.signin);
        btsignup = (TextView) findViewById(R.id.register);
        mFirebaselistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                if( !(firebaseUser == null) )
                {
                    startActivity(new Intent(login.this, home.class));
                    finish();
                }
            }
        };
        btsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = email.getText().toString();
                String passwordid = password.getText().toString();
                 if( emailid == null || passwordid == null )
                {
                    Toast.makeText(login.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else if( ( !(emailid == null) && !(passwordid == null) ) )
                {
                    mFirebaseAuth.signInWithEmailAndPassword(emailid, passwordid).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!(task.isComplete()))
                            {
                                Toast.makeText(login.this, "LogIn Error, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else if(task.isComplete())
                            {
                                startActivity(new Intent(login.this, home.class));
                                Toast.makeText(login.this, "Logged In", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }
                    });
                }
            }
        });
        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, MainActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mFirebaselistener);
    }
}
