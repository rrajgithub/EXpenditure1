package com.example.startup1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Set;

public class home extends AppCompatActivity {

    Button bt, dt, cp, adta,gimo,erda;
    SharedPreferences sharedPreferences;
    Set<String> hash_Set;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bt = (Button)findViewById(R.id.logout);
        dt = (Button)findViewById(R.id.delete);
        cp = (Button)findViewById(R.id.changepassword);
        adta = (Button)findViewById(R.id.addmoney);
        gimo = (Button)findViewById(R.id.GivenMoney);
        erda = (Button)findViewById(R.id.erasedata);

        TextView d=(TextView)findViewById(R.id.Wastedmoney);
        final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        final String email1 = user1.getDisplayName();
        try {

            sharedPreferences = getSharedPreferences(email1, Context.MODE_PRIVATE);
            long fetch = sharedPreferences.getLong(email1, 0);
            if(fetch < 0 )
            {
                d.setText("DONATED MONEY : Rs " + (-1 * fetch));
            }
            else {
                d.setText("WASTED MONEY : Rs " + fetch);
            }
        }
        catch(Exception e)
        {
            Log.i("Wastage","0");
        }
        gimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                TextView text= (TextView)findViewById(R.id.add);
                long total = Long.parseLong(text.getText().toString());
                text.setText("");
                text.setHint("ENTER WASTED OR DONATED MONEY");
                sharedPreferences = getSharedPreferences(email1, Context.MODE_PRIVATE);
                long fetch = sharedPreferences.getLong(email1, 0);
                fetch = fetch - total;
                TextView d=(TextView)findViewById(R.id.Wastedmoney);
                if(fetch < 0 )
                {
                    d.setText("DONATED MONEY : Rs " + (-1 * fetch));
                }
                else {
                    d.setText("WASTED MONEY : Rs " + fetch);

                }
                editor.putLong(email1,fetch).apply();
                editor.commit();
            }
        });
                adta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                TextView text= (TextView)findViewById(R.id.add);
                long total = Long.parseLong(text.getText().toString());
                text.setText("");
                text.setHint("ENTER THE WASTED OR DONATED AMOUNT");
                sharedPreferences = getSharedPreferences(email1, Context.MODE_PRIVATE);
                long fetch = sharedPreferences.getLong(email1, 0);
                fetch = fetch + total;
                TextView d=(TextView)findViewById(R.id.Wastedmoney);
                if(fetch < 0 )
                {
                    d.setText("DONATED MONEY : Rs " + (-1 * fetch));
                }
                else {
                    d.setText("WASTED MONEY : Rs " + fetch);
                }
                editor.putLong(email1,fetch).apply();
                editor.commit();

            }
        });
        erda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putLong(email1,0).apply();
                editor.commit();
                TextView text= (TextView)findViewById(R.id.Wastedmoney);
                text.setText("WASTED MONEY : Rs 0");
            }
        });
        //RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* try {
            List<String> list = new ArrayList<String>(fetch);
            Log.i("Value",list.get(0));
            Log.e("value"," puneet");
            String str[] = (String[]) list.toArray();
            recyclerView.setAdapter(new ProgrammingAdapter(str));
        }
        catch(Exception e)
        {
            Log.e("puneet","puneet");
        }*/

        Log.e("value","my");
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String email = user.getDisplayName();
        Toast.makeText(this, email +" ", Toast.LENGTH_SHORT).show();
            //TextView em = (TextView) findViewById(R.id.email);
           // em.setText(email);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(home.this, "Bye User", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(home.this, login.class));
                finish();
            }
        });
        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(home.this, "Account Deleted!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(home.this,login.class));
                                    finish();
                                }
                            }
                        });
            }
        });
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, changepassword.class));
                finish();
            }
        });
    }
}
