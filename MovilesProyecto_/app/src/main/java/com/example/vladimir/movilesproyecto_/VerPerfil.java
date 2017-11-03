package com.example.vladimir.movilesproyecto_;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;



public class VerPerfil extends AppCompatActivity {
    private String[] arraySpinner;
    Intent i;
    TextView Nombre_TextView;
    TextView Email_TextView;
    TextView Password_TextView;
    TextView Puntos_TextView;


    TextView getNombre;
    TextView getEmail;
    TextView getPassword;
    TextView getPuntos;


    ArrayList<TextView> textViewsVal;
    ArrayList<TextView> textViewsName;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);
        Nombre_TextView=(TextView)findViewById(R.id.Nombre_textView);
        Email_TextView=(TextView)findViewById(R.id.Email_TextView);
        Password_TextView=(TextView)findViewById(R.id.Password_TextView);
        Puntos_TextView=(TextView)findViewById(R.id.Puntos_TextView);

        getNombre=(TextView)findViewById(R.id.Nombre_valor);
        getEmail=(TextView)findViewById(R.id.Email_valor);
        getPassword=(TextView)findViewById(R.id.Password_valor);
        getPuntos=(TextView)findViewById(R.id.Puntos_valor);

        textViewsName = new ArrayList<TextView>();
        textViewsName.add(Nombre_TextView);
        textViewsName.add(Email_TextView);
        textViewsName.add(Password_TextView);
        textViewsName.add(Puntos_TextView);

        textViewsVal = new ArrayList<TextView>();
        textViewsVal.add(getNombre);
        textViewsVal.add(getEmail);
        textViewsVal.add(getPassword);
        textViewsVal.add(getPuntos);
        Intent name_intent=this.getIntent();
        final String name=name_intent.getExtras().getString("name");

        reference = database.getReference(name);
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //rutina.setText(dataSnapshot.getKey());
                int i = 0;
                Log.d("number of childs",""+dataSnapshot.getChildrenCount());

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if (i < 10) {
                        textViewsName.get(i).setText(child.getKey());
                        textViewsVal.get(i).setText(child.getValue().toString());
                        Log.d("User key", child.getKey());
                        Log.d("User ref", child.getRef().toString());
                        Log.d("User val", child.getValue().toString());
                        i++;
                    }

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    }
